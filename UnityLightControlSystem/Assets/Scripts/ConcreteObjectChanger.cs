using com.hp.hpl.jena.rdf.model;
using jenaInterface;

namespace LightControlSystem
{
    /*
     * C# implementation of Java object changer class. C# does not allow anonymous classes so callback functions are used instead.
     * Author: Luke Tudor
     * Date: October 2018
     */
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
