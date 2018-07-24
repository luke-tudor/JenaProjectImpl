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

        protected override void apply(Statement s)
        {
            _callback(s);
        }

        protected override string propertyName()
        {
            return _propertyName;
        }

        protected override string resourceName()
        {
            return _resourceName;
        }
    }
}
