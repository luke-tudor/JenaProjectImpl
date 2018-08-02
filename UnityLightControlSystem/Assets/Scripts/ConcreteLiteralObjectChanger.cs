using com.hp.hpl.jena.rdf.model;
using jenaInterface;

namespace LightControlSystem
{
    public class ConcreteObjectChanger : ObjectChanger
    {
        private readonly StatementConsumer _callback;

        public ConcreteObjectChanger(StatementConsumer callback)
        {
            _callback = callback;
        }

        public void apply(Statement s)
        {
            _callback(s);
        }
    }
}
