package org.cc.model;

import org.cc.json.CCPath;
import org.cc.json.JSONArray;

import lombok.extern.log4j.Log4j2;

/**
 *
 * @author william
 */
@Log4j2
public class CCProcUtils {

    private final static String CHK_STATUS = "$check:status";

    /**
     * @param proc
     * @param cmdString
     * @return
     */
    public static Object exec(CCProcObject proc, String cmdString) {
        Object ret = null;

        CCCmdProcString cmd = new CCCmdProcString(cmdString);
        try {
            if (!"$".equals(cmd.inParam())) {
                proc.put("$$", proc.get("$"));// 客制化處理
                proc.put("$", proc.get(cmd.inParam()));
            }
            ret = CCFunc.apply(cmd.funId(), proc, cmd.params());
            if (ret != null) {
                CCPath.set(proc, cmd.outParam(), ret);
            }
        } catch (Exception e) {
            log.info("Can't apply :" + cmd, e);
            setException(proc, cmd.funId(), e.getMessage());
        }

        return ret;
    }

    public static void each(CCProcObject proc, String cmdString) {
        CCCmdProcString cmd = new CCCmdProcString(cmdString);
        JSONArray data = ("$".equals(cmd.inParam())) ? proc.optJSONArray("$data") : proc.optJSONArray(cmd.inParam());
        if (data != null) {
            int idx = 0 ;
            for (Object o : data) {
                proc.put("$", o);
                exec(proc, cmdString);
                System.out.println("===== item idx : "+idx++);
            }
        }
    }

    public static void setException(CCProcObject proc, String funId, String msg) {
        CCPath.set(proc, CHK_STATUS, "E");
        CCPath.set(proc, "$check:exception", msg);
        CCPath.set(proc, "$check:funId", funId);
    }

}
