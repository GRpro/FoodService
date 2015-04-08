package com.foodservice.entities.data;


public enum OrderingStatus {
    /**
     * NEW - just a new ordering not accepted by any Manager
     * ACCEPTED - ordering which is in process of servicing
     * REFUSED - not accepted ordering
     * FINISHED - successfully finished ordering
     * PROBLEMATIC - ordering with which there is some problem after it was accepted
     */
    NEW, ACCEPTED, REFUSED, FINISHED, PROBLEMATIC,
}
