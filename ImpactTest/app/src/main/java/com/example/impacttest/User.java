package com.example.impacttest;

public class User {
        private String address;
        private String groupName;
        private Integer groupId;
        private String identifier;
        private String protocol;
        private String serialNumber;

        public User(String address, String groupName, Integer groupId, String identifier, String protocol, String serialNumber) {
            this.address = address;
            this.groupName = groupName;
            this.groupId = groupId;
            this.identifier = identifier;
            this.protocol = protocol;
            this.serialNumber = serialNumber;
        }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
