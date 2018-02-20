package io.mincongh.client;

/**
 * @author Mincong Huang
 */
public interface RoomScheduler {
  boolean canAcceptCapacityFor(Meeting meeting);
}
