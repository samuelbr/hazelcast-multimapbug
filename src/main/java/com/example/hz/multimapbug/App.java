package com.example.hz.multimapbug;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.transaction.TransactionContext;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) {
		
		final String NAME = "test";
		final String KEY = "key";
		final String VALUE = "value";
		
		HazelcastInstance instance = Hazelcast.newHazelcastInstance();
		
		HazelcastInstance client = HazelcastClient.newHazelcastClient();
		
		client.getMultiMap(NAME).put(KEY, VALUE);
		TransactionContext tx = client.newTransactionContext();
		
		tx.beginTransaction();
		tx.getMultiMap(NAME).remove(KEY, VALUE);
		tx.commitTransaction();
		
		instance.shutdown();
	}
}
