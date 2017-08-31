package com.cmacgm;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import com.cmacgm.model.Application;
import com.cmacgm.model.ApplicationUrl;
import com.cmacgm.repository.ApplicationRepository;
import com.cmacgm.repository.ApplicationUrlRepository;

@Configuration
@EnableScheduling
public class PingServiceJob implements SchedulingConfigurer {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(PingServiceJob.class);

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	ApplicationUrlRepository applicationUrlRepository;

	@Autowired
	ApplicationContext ctx;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		LOGGER.debug("adding applicationRepository job");

		List<com.cmacgm.model.Application> application = applicationRepository.findAll();
		if (application != null) {
			for (Application configApplication : application) {

				long delay = configApplication.getSyncJobInitialDelay();
				long rate = configApplication.getSyncJobRate();
				taskRegistrar.addFixedRateTask(new IntervalTask(new Runnable() {

					public void run() {
						LOGGER.debug("running applicationRepository job");
						try {

							for (ApplicationUrl applicationUrl : configApplication.getApplicationUrl()) {
								if (applicationUrl != null &&applicationUrl.getServerType().getName().equalsIgnoreCase("web")
										|| applicationUrl.getServerType().getName().equalsIgnoreCase("webservices")) {
									String statusCode = "400";
									statusCode = pingUrl(applicationUrl.getApplicationUrl());

									if (statusCode != null 	&& !applicationUrl.getStatusCode().equalsIgnoreCase(statusCode)) {
										applicationUrl.setStatusCode(statusCode);
										if (statusCode.equalsIgnoreCase("200"))
											applicationUrl.setStatus(true);
										else
											applicationUrl.setStatus(false);
										configApplication.setLastUpdatedTime(new Date());
										applicationUrlRepository.save(applicationUrl);
									}
								} else if (applicationUrl!=null && applicationUrl.getServerType().getName().equalsIgnoreCase("db") && applicationUrl.getIpAddress()!=null && applicationUrl.getHostPortNumber()!=null) {
									String statusCode = "400";
									statusCode = isSocketAliveUtility(applicationUrl.getIpAddress(),applicationUrl.getHostPortNumber());

									if (statusCode != null && !applicationUrl.getStatusCode().equalsIgnoreCase(statusCode)) {
										applicationUrl.setStatusCode(statusCode);
										if (statusCode.equalsIgnoreCase("200"))
											applicationUrl.setStatus(true);
										else
											applicationUrl.setStatus(false);
										configApplication.setLastUpdatedTime(new Date());
										applicationUrlRepository.save(applicationUrl);
									}
								}

							}
							configApplication.setLastSyncTime(new Date());
							applicationRepository.save(configApplication);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}, rate, delay));
			}
		}
	}

	private String pingUrl(String address) {

		int responseCode = 404;
		try {
			URL url = new URL(address);

			URLConnection urlConnection = url.openConnection();
			urlConnection.setConnectTimeout(2000);
			urlConnection.setReadTimeout(2000);

			if (address.startsWith("http")) {
				responseCode = ((HttpURLConnection) urlConnection).getResponseCode();
			} else {
				responseCode = ((HttpsURLConnection) urlConnection).getResponseCode();
			}

		} catch (ConnectException e) {
			return String.valueOf(responseCode);
		} catch (SocketTimeoutException e) {
			return String.valueOf(responseCode);
		} catch (IOException e) {
			return String.valueOf(responseCode);
		} catch (Exception e) {
			return String.valueOf(responseCode);
		}
		return String.valueOf(responseCode);

	}

	public String isSocketAliveUtility(String hostName, String hostPortNumber) {
		String responseCode = "404";
		// Creates a socket address from a hostname and a port number
		SocketAddress socketAddress = new InetSocketAddress(hostName, Integer.parseInt(hostPortNumber));
		Socket socket = new Socket();
		// Timeout required - it's in milliseconds
		int timeout = 2000;

		try {
			socket.connect(socketAddress, timeout);
			socket.close();
			responseCode = "200";

		} catch (ConnectException e) {
			return responseCode;
		} catch (SocketTimeoutException e) {
			return responseCode;
		} catch (IOException e) {
			return responseCode;
		} catch (Exception e) {
			return responseCode;
		}
		return responseCode;
	}

}
