using com.hp.hpl.jena.rdf.model;
using jenaInterface;

namespace LightControlSystem
{
    /*
     * C# implementation of Java listener class. C# does not allow anonymous classes so callback functions are used instead.
     * Author: Luke Tudor
     * Date: October 2018
     */
    public class ConcreteListener : Listener
    {
        private readonly string _resourceName;
        private readonly string _propertyName;
        private readonly StatementConsumer _callback;

        public ConcreteListener(string resourceName, string propertyName, StatementConsumer callback)
        {
            _resourceName = resourceName;
            _propertyName = propertyName;
            _callback = callback;
        }

        public string resourceName()
        {
            return _resourceName;
        }

        public string propertyName()
        {
            return _propertyName;
        }

        public void apply(Statement s)
        {
            _callback(s);
        }
    }
}
