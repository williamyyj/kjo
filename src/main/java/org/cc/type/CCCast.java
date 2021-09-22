package org.cc.type;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class CCCast {
    public static int _int(Object o, int dv) {
        try {
            if (o instanceof Number) {
                return ((Number) o).intValue();
            } else if (o instanceof String) {
                String str = ((String) o).trim();
                return str.length() > 0 ? Integer.parseInt(str) : dv;
            }
        } catch (Exception e) {
            log.warn("Can't cast " + o);
        }
        return dv;
    }

    public static long _long(Object o, long dv) {
        try {
            if (o instanceof Number) {
                return ((Number) o).longValue();
            } else if (o instanceof String) {
                String str = ((String) o).trim();
                return str.length() > 0 ? Long.parseLong(str) : dv;
            }
        } catch (Exception e) {
            log.warn("Can't cast " + o);
        }
        return dv;
    }

    public static double _double(Object o, double dv) {
        try {
            if (o instanceof Number) {
                return ((Number) o).doubleValue();
            } else if (o instanceof String) {
                String str = ((String) o).trim();
                return str.length() > 0 ? Double.parseDouble(str) : dv;
            }
        } catch (Exception e) {
            log.warn("Can't cast " + o);
        }
        return dv;
    }

    public static boolean _bool(Object o, boolean dv) {
        if (o instanceof Boolean) {
            return (Boolean) o;
        } else if (o instanceof String) {
            return Boolean.parseBoolean(((String) o).trim());
        } else {
            return dv;
        }
    }

    public static String _string(Object o, String dv) {
        return (o != null) ? o.toString().trim() : dv;
    }

    public static String _string(Object o) {
        return _string(o, "");
    }
}
