package com.segment;

import org.joda.time.DateTime;

import com.segment.models.BasePayload;
import com.segment.models.Callback;
import com.segment.models.Context;
import com.segment.models.EventProperties;
import com.segment.models.Traits;

public class Analytics {

	private static Client defaultClient;

	/**
	 * Creates a new Segment.io client.
	 * 
	 * The client is an HTTP wrapper over the Segment.io REST API. It will allow
	 * you to conveniently consume the API without making any HTTP requests
	 * yourself.
	 * 
	 * This client is also designed to be thread-safe and to not block each of
	 * your calls to make a HTTP request. It uses batching to efficiently send
	 * your requests on a separate resource-constrained thread pool.
	 * 
	 * 
	 * @param secret
	 *            Your segment.io secret. You can get one of these by
	 *            registering for a project at https://segment.io
	 * 
	 */
	public static void initialize(String secret) {

		defaultClient = new Client(secret, new Options());
	}

	/**
	 * Creates a new Segment.io client.
	 * 
	 * The client is an HTTP wrapper over the Segment.io REST API. It will allow
	 * you to conveniently consume the API without making any HTTP requests
	 * yourself.
	 * 
	 * This client is also designed to be thread-safe and to not block each of
	 * your calls to make a HTTP request. It uses batching to efficiently send
	 * your requests on a separate resource-constrained thread pool.
	 * 
	 * 
	 * @param secret
	 *            Your segment.io secret. You can get one of these by
	 *            registering for a project at https://segment.io
	 * 
	 * @param options
	 *            Options to configure the behavior of the Segment.io client
	 * 
	 * 
	 */
	public static void initialize(String secret, Options options) {

		defaultClient = new Client(secret, options);
	}

	private static void checkInitialized() {

		if (defaultClient == null) {
			throw new IllegalStateException("Analytics client is "
					+ "not initialized. Please call Analytics.iniitalize(..); "
					+ "before calling identify / track / or flush.");
		}
	}

	//
	// API Calls
	//

	//
	// Identify
	//

	/**
	 * Identifying a user ties all of their actions to an id, and associates
	 * user traits to that id.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param traits
	 *            a dictionary with keys like subscriptionPlan or age. You only
	 *            need to record a trait once, no need to send it again.
	 */
	public static void identify(String userId, Traits traits) {
		checkInitialized();
		defaultClient.identify(userId, traits, null, null, null);
	}

	/**
	 * Identifying a user ties all of their actions to an id, and associates
	 * user traits to that id.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param traits
	 *            a dictionary with keys like subscriptionPlan or age. You only
	 *            need to record a trait once, no need to send it again.
	 * 
	 * @param context
	 *            an object that describes anything that doesn't fit into this
	 *            event's properties (such as the user's IP)
	 * 
	 */
	public static void identify(String userId, Traits traits, Context context) {
		checkInitialized();
		defaultClient.identify(userId, traits, null, context, null);
	}

	/**
	 * Identifying a user ties all of their actions to an id, and associates
	 * user traits to that id.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param traits
	 *            a dictionary with keys like subscriptionPlan or age. You only
	 *            need to record a trait once, no need to send it again.
	 * 
	 * @param timestamp
	 *            a {@link DateTime} representing when the identify took place.
	 *            If the identify just happened, leave it blank and we'll use
	 *            the server's time. If you are importing data from the past,
	 *            make sure you provide this argument.
	 * 
	 * @param context
	 *            an object that describes anything that doesn't fit into this
	 *            event's properties (such as the user's IP)s
	 * 
	 */
	public static void identify(String userId, Traits traits,
			DateTime timestamp, Context context) {
		checkInitialized();
		defaultClient.identify(userId, traits, timestamp, context, null);
	}

	/**
	 * Identifying a user ties all of their actions to an id, and associates
	 * user traits to that id.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param traits
	 *            a dictionary with keys like subscriptionPlan or age. You only
	 *            need to record a trait once, no need to send it again.
	 * 
	 * @param timestamp
	 *            a {@link DateTime} representing when the identify took place.
	 *            If the identify just happened, leave it blank and we'll use
	 *            the server's time. If you are importing data from the past,
	 *            make sure you provide this argument.
	 * 
	 * @param context
	 *            an object that describes anything that doesn't fit into this
	 *            event's properties (such as the user's IP)
	 * 
	 * @param callback
	 *            a callback that is fired when this track's batch is flushed to
	 *            the server. Note: this callback is fired on the same thread as
	 *            the async event loop that made the request. You should not
	 *            perform any kind of long running operation on it.
	 */
	public static void identify(String userId, Traits traits,
			DateTime timestamp, Context context, Callback callback) {
		checkInitialized();
		defaultClient.identify(userId, traits, timestamp, context, callback);
	}

	//
	// Track
	//

	/**
	 * Whenever a user triggers an event, you’ll want to track it.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param event
	 *            describes what this user just did. It's a human readable
	 *            description like "Played a Song", "Printed a Report" or
	 *            "Updated Status".
	 * 
	 * @param properties
	 *            a dictionary with items that describe the event in more
	 *            detail. This argument is optional, but highly
	 *            recommended—you’ll find these properties extremely useful
	 *            later.
	 */
	public static void track(String userId, String event,
			EventProperties properties) {
		checkInitialized();
		defaultClient.track(userId, event, properties, null, null, null);
	}

	/**
	 * Whenever a user triggers an event, you’ll want to track it.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param event
	 *            describes what this user just did. It's a human readable
	 *            description like "Played a Song", "Printed a Report" or
	 *            "Updated Status".
	 * 
	 */
	public static void track(String userId, String event) {
		checkInitialized();
		defaultClient.track(userId, event, null, null, null, null);
	}

	/**
	 * Whenever a user triggers an event, you’ll want to track it.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param properties
	 *            a dictionary with items that describe the event in more
	 *            detail. This argument is optional, but highly
	 *            recommended—you’ll find these properties extremely useful
	 *            later.
	 * 
	 * @param event
	 *            describes what this user just did. It's a human readable
	 *            description like "Played a Song", "Printed a Report" or
	 *            "Updated Status".
	 * 
	 * @param timestamp
	 *            a {@link DateTime} object representing when the track took
	 *            place. If the event just happened, leave it blank and we'll
	 *            use the server's time. If you are importing data from the
	 *            past, make sure you provide this argument.
	 * 
	 */
	public static void track(String userId, String event,
			EventProperties properties, DateTime timestamp) {
		checkInitialized();
		defaultClient.track(userId, event, properties, timestamp, null, null);
	}

	/**
	 * Whenever a user triggers an event, you’ll want to track it.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param event
	 *            describes what this user just did. It's a human readable
	 *            description like "Played a Song", "Printed a Report" or
	 *            "Updated Status".
	 * 
	 * @param properties
	 *            a dictionary with items that describe the event in more
	 *            detail. This argument is optional, but highly
	 *            recommended—you’ll find these properties extremely useful
	 *            later.
	 * 
	 * @param timestamp
	 *            a {@link DateTime} object representing when the track took
	 *            place. If the event just happened, leave it blank and we'll
	 *            use the server's time. If you are importing data from the
	 *            past, make sure you provide this argument.
	 * 
	 * @param context
	 *            an object that describes anything that doesn't fit into this
	 *            event's properties (such as the user's IP)
	 * 
	 */
	public static void track(String userId, String event,
			EventProperties properties, DateTime timestamp, Context context) {
		checkInitialized();
		defaultClient
				.track(userId, event, properties, timestamp, context, null);
	}

	/**
	 * Whenever a user triggers an event, you’ll want to track it.
	 * 
	 * @param userId
	 *            the user's id after they are logged in. It's the same id as
	 *            which you would recognize a signed-in user in your system.
	 * 
	 * @param event
	 *            describes what this user just did. It's a human readable
	 *            description like "Played a Song", "Printed a Report" or
	 *            "Updated Status".
	 * 
	 * @param properties
	 *            a dictionary with items that describe the event in more
	 *            detail. This argument is optional, but highly
	 *            recommended—you’ll find these properties extremely useful
	 *            later.
	 * 
	 * @param timestamp
	 *            a {@link DateTime} object representing when the track took
	 *            place. If the event just happened, leave it blank and we'll
	 *            use the server's time. If you are importing data from the
	 *            past, make sure you provide this argument.
	 * 
	 * @param context
	 *            an object that describes anything that doesn't fit into this
	 *            event's properties (such as the user's IP)
	 * 
	 * @param callback
	 *            a callback that is fired when this track's batch is flushed to
	 *            the server. Note: this callback is fired on the same thread as
	 *            the async event loop that made the request. You should not
	 *            perform any kind of long running operation on it.
	 */
	public static void track(String userId, String event,
			EventProperties properties, DateTime timestamp, Context context,
			Callback callback) {
		checkInitialized();
		defaultClient.track(userId, event, properties, timestamp, context,
				callback);
	}

	/**
	 * Enqueue an identify or track payload
	 * 
	 * @param payload
	 */
	public void enqueue(BasePayload payload) {
		defaultClient.enqueue(payload);
	}

	//
	// Flush Actions
	//

	/**
	 * Flushes the current contents of the queue
	 */
	public static void flush() {
		checkInitialized();
		defaultClient.flush();
	}

	/**
	 * Closes the threads associated with the client
	 */
	public static void close() {
		checkInitialized();
		defaultClient.close();
	}

	/**
	 * Fetches the default analytics client singleton
	 * 
	 * @return
	 */
	public static Client getDefaultClient() {
		return defaultClient;
	}

}