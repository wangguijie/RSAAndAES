package com.yinlib.generate.utils;

import java.util.List;

/**
 * $todo$
 *
 * @user Jay Wang
 * @date 2018-08-20 15:48
 */
public class LicenseInfo {

    /**
     * company : CompanyNameHere
     * company_id : CompanyIDHere
     * type : redistribute || leaf
     * product : ProductNameIncludedInPorductList
     * model_secret : true
     * capability : {"track":true}
     * limit : {"uuid":"test_uuid","expiration":[20160501,20160630],"appid":["com.example.app1","com.test.*"],"cores":4,"_hardware_auth":"athsa204a","_hardware_auth_key":"YWQwYWE0NWVhM2Y5MDNkOWM4ZDQxN2U4NmRmMDU1MzE="}
     * counter : {"threads":4}
     */

    private String company;
    private String company_id;
    private String type;
    private String product;
    private boolean model_secret;
    private CapabilityBean capability;
    private LimitBean limit;
    private CounterBean counter;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public boolean isModel_secret() {
        return model_secret;
    }

    public void setModel_secret(boolean model_secret) {
        this.model_secret = model_secret;
    }

    public CapabilityBean getCapability() {
        return capability;
    }

    public void setCapability(CapabilityBean capability) {
        this.capability = capability;
    }

    public LimitBean getLimit() {
        return limit;
    }

    public void setLimit(LimitBean limit) {
        this.limit = limit;
    }

    public CounterBean getCounter() {
        return counter;
    }

    public void setCounter(CounterBean counter) {
        this.counter = counter;
    }

    public static class CapabilityBean {
        /**
         * track : true
         */

        private boolean track;

        public boolean isTrack() {
            return track;
        }

        public void setTrack(boolean track) {
            this.track = track;
        }
    }

    public static class LimitBean {
        /**
         * uuid : test_uuid
         * expiration : [20160501,20160630]
         * appid : ["com.example.app1","com.test.*"]
         * cores : 4
         * _hardware_auth : athsa204a
         * _hardware_auth_key : YWQwYWE0NWVhM2Y5MDNkOWM4ZDQxN2U4NmRmMDU1MzE=
         */

        private String uuid;
        private int cores;
        private String _hardware_auth;
        private String _hardware_auth_key;
        private List<Integer> expiration;
        private List<String> appid;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public int getCores() {
            return cores;
        }

        public void setCores(int cores) {
            this.cores = cores;
        }

        public String get_hardware_auth() {
            return _hardware_auth;
        }

        public void set_hardware_auth(String _hardware_auth) {
            this._hardware_auth = _hardware_auth;
        }

        public String get_hardware_auth_key() {
            return _hardware_auth_key;
        }

        public void set_hardware_auth_key(String _hardware_auth_key) {
            this._hardware_auth_key = _hardware_auth_key;
        }

        public List<Integer> getExpiration() {
            return expiration;
        }

        public void setExpiration(List<Integer> expiration) {
            this.expiration = expiration;
        }

        public List<String> getAppid() {
            return appid;
        }

        public void setAppid(List<String> appid) {
            this.appid = appid;
        }
    }

    public static class CounterBean {
        /**
         * threads : 4
         */

        private int threads;

        public int getThreads() {
            return threads;
        }

        public void setThreads(int threads) {
            this.threads = threads;
        }
    }
}
