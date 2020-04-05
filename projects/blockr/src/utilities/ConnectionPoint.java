package utilities;

public enum ConnectionPoint {

    TOPSOCKET, BOTTOMPLUG, RIGHTSOCKET, LEFTPLUG, CAVITYSOCKET, CAVITYPLUG;

    /**
     * Check if two ConnectionPoints are compatible
     * @param cp The connectionPoint for which compatibility with this needs to be checked
     * @return true if the connections are compatible, false otherwise
     */
    public boolean compatible(ConnectionPoint cp){
        switch(this){
            case TOPSOCKET:
                if (cp.equals(ConnectionPoint.BOTTOMPLUG)) return true;
                else return false;
            case BOTTOMPLUG:
                if (cp.equals(ConnectionPoint.TOPSOCKET)) return true;
                else return false;
            case RIGHTSOCKET:
                if (cp.equals(ConnectionPoint.LEFTPLUG)) return true;
                else return false;
            case LEFTPLUG:
                if (cp.equals(ConnectionPoint.RIGHTSOCKET)) return true;
                else return false;
            case CAVITYSOCKET:
                if (cp.equals(ConnectionPoint.CAVITYPLUG)) return true;
                else return false;
            case CAVITYPLUG:
                if (cp.equals(ConnectionPoint.CAVITYSOCKET)) return true;
                else return false;
            default:
                return false;
        }
    }
}
