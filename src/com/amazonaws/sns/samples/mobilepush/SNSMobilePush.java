package com.amazonaws.sns.samples.mobilepush;

/*
 * Copyright 2014 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.sns.samples.tools.AmazonSNSClientWrapper;
import com.amazonaws.sns.samples.tools.SampleMessageGenerator.Platform;

public class SNSMobilePush {

	private AmazonSNSClientWrapper snsClientWrapper;

	public SNSMobilePush(AmazonSNS snsClient) {
		this.snsClientWrapper = new AmazonSNSClientWrapper(snsClient);
	}

	public static final Map<Platform, Map<String, MessageAttributeValue>> attributesMap = new HashMap<Platform, Map<String, MessageAttributeValue>>();
	static {
		attributesMap.put(Platform.ADM, null);
		attributesMap.put(Platform.GCM, null);
		attributesMap.put(Platform.APNS, null);
		attributesMap.put(Platform.APNS_SANDBOX, null);
		//attributesMap.put(Platform.BAIDU, addBaiduNotificationAttributes());
		//attributesMap.put(Platform.WNS, addWNSNotificationAttributes());
		//attributesMap.put(Platform.MPNS, addMPNSNotificationAttributes());
	}

	public static void main(String[] args) throws IOException {
		/*
		 * TODO: Be sure to fill in your AWS access credentials in the
		 * AwsCredentials.properties file before you try to run this sample.
		 * http://aws.amazon.com/security-credentials
		 */
		AmazonSNS sns = new AmazonSNSClient(new PropertiesCredentials(
				SNSMobilePush.class
						.getResourceAsStream("AwsCredentials.properties")));

		sns.setEndpoint("https://sns.us-west-2.amazonaws.com");
		System.out.println("===========================================\n");
		System.out.println("Getting Started with Amazon SNS");
		System.out.println("===========================================\n");
		try {
			SNSMobilePush sample = new SNSMobilePush(sns);
			/* TODO: Uncomment the services you wish to use. */
			sample.demoAndroidAppNotification();
			// sample.demoKindleAppNotification();
			// sample.demoAppleAppNotification();
			// sample.demoAppleSandboxAppNotification();
			// sample.demoBaiduAppNotification();
			// sample.demoWNSAppNotification();
			// sample.demoMPNSAppNotification();
		} catch (AmazonServiceException ase) {
			System.out
					.println("Caught an AmazonServiceException, which means your request made it "
							+ "to Amazon SNS, but was rejected with an error response for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out
					.println("Caught an AmazonClientException, which means the client encountered "
							+ "a serious internal problem while trying to communicate with SNS, such as not "
							+ "being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
	}

	public void demoAndroidAppNotification() {
		// TODO: Please fill in following values for your application. You can
		// also change the notification payload as per your preferences using
		// the method
		// com.amazonaws.sns.samples.tools.SampleMessageGenerator.getSampleAndroidMessage()
		String serverAPIKey = "SERVER_API_KEY";
		String applicationName = "APP_NAME";
		String registrationId = "ADD_THE_REG_KEY";
		Platform platform = Platform.GCM;
		snsClientWrapper.demoNotification(platform, "", serverAPIKey,
				registrationId, applicationName, attributesMap);
	}

}
