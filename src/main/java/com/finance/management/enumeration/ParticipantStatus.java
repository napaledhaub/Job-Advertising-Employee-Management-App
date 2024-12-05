package com.finance.management.enumeration;

public enum ParticipantStatus {
    REGISTERED {
        @Override
        public String toString() {
            return "REGISTERED";
        }
    },
    NOT_VALIDATED {
        @Override
        public String toString() {
            return "NOT VALIDATED";
        }
    },
    NOT_REGISTERED {
        @Override
        public String toString() {
            return "NOT REGISTERED";
        }
    }
}
