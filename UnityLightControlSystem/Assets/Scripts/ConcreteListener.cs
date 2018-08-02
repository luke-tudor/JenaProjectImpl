using com.hp.hpl.jena.rdf.model;
using jenaInterface;

namespace LightControlSystem
{
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
