package com.automation.backend.utils.DataUtils;

import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A Collection
 *
 * @author Ashish Kumar Bajpai
 * @company Myntra Design Pvt Ltd
 * @since November-2013
 * updated By @Abhijit.pAti
 */
public class DBUtilities {

	static org.slf4j.Logger log = LoggerFactory.getLogger(DBUtilities.class);
    static HashMap<String, String> getDB = new HashMap<>();
    static{
        getDB.put("lms", "myntra_lms");
        getDB.put("rms", "myntra_rms");
        getDB.put("oms", "myntra_oms");
        getDB.put("rds", "myntra_rds");
        getDB.put("atp", "myntra_atp");
        getDB.put("order", "myntra_order");
        getDB.put("tools", "myntra_tools");
        getDB.put("ims", "myntra_ims");
        getDB.put("portal", "myntra");
        getDB.put("wms", "myntra_wms");
        getDB.put("lp", "myntra_lp");
        getDB.put("pps", "myntra_pps");
        // Making temp fix for MT as myntra orders are getting saved in myntra_pps2 db
        getDB.put("pps2", "myntra_pps2");
        getDB.put("sps", "myntra_sps");
        getDB.put("silkroute", "myntra_silkroute");
        getDB.put("vms", "myntra_vms");
        getDB.put("seller1", "myntra_seller1");
        getDB.put("seller", "myntra_seller");
        getDB.put("test", "myntra_test");
        getDB.put("artie", "artie");
       // getDB.put("notifications_revamp", "myntra_notification");
        getDB.put("notification", "myntra_notification");
        getDB.put("terms", "terms");
        getDB.put("contacts", "myntra_contacts");
        getDB.put("giftcard","myntra_giftcard");
        getDB.put("jabong_giftcard","jabong_giftcard");
        getDB.put("contracts", "contracts");
        getDB.put("pricingengine", "pricingengine");
        getDB.put("myntra_seller1", "myntra_seller");
        getDB.put("jabong_pps", "jabong_pps");
        getDB.put("myntra_payments", "payments");
        getDB.put("jabong_payments", "jabong_payments");
        getDB.put("jabong_address", "jabong_address");
        getDB.put("myntra_address", "myntra_address");
        getDB.put("jabong_referral", "referral_jabong");
        getDB.put("myntra_referral", "referral");
    }


	/**
	 * Execute Select Queries
	 *
	 * @param query
	 * @param DB
	 *            i.e OMS, LMS, VMS, IMS, ATP
	 * @return
	 * @throws SQLException
	 */

	public static Map<String, Object> exSelectQueryForSingleRecord(String query, String DB){
		List resultSet = null;
		HashMap<String, Object> hm = null;
		log.debug(" exSelectQueryForSingleRecord QUERY :-" + query);
        if(getDB.containsKey(DB.toLowerCase())){
            DB = ""+getDB.get(DB.toLowerCase());
        }
//        resultSet = SystemConfigProvider.getTopology().getDatabaseService(DB).queryForList(query);
        if(resultSet.isEmpty()){
            return null;
        }

        hm = (LinkedHashMap<String, Object>) resultSet.get(0);
		return hm;
	}



}