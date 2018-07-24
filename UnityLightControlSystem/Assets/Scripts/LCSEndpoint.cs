using jenaInterface;

namespace LightControlSystem
{
    public class LCSEndpoint
    {
        public static readonly string PREFIX = "http://www.semanticweb.org/ontologies/2008/9/SensorNetwork_3_4.owl#";
        public static readonly JenaController lcs = new JenaController("c:/Users/USER/Desktop/softeng700/projectcode/LCS.owl", "file:///C:/Users/USER/Desktop/softeng700/projectcode/lcs-rules.txt");
    }
}