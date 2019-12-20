public class IPLFactory {
    public static IPLAdapter createObject(SortingTypes SortingTypes ) {
        if(SortingTypes.equals(SortingTypes.BATSMAN))
        {
            return new BatsmanAdaptor();
        }
        return new BowlerAdaptor();
    }
}
