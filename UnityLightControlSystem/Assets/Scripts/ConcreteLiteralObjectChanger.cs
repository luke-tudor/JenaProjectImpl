using com.hp.hpl.jena.rdf.model;
using jenaInterface;

namespace LightControlSystem
{
    public class ConcreteLiteralObjectChanger : LiteralObjectChanger
    {
        private readonly StatementConsumer _callback;

        public ConcreteLiteralObjectChanger(StatementConsumer callback)
        {
            _callback = callback;
        }

        public void apply(Statement s)
        {
            _callback(s);
        }
    }
}
