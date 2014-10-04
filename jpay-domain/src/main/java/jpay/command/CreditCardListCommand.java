package jpay.command;

import jpay.domain.CreditCardSource;
import jpay.model.CreditCard;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

/**
 * CreditCardListCommand
 */
@Command(scope = "event", name = "list", description = "list events")
public class CreditCardListCommand implements Action
{
    private CreditCardSource creditCardSource;

    @Argument(index = 0, name = "name", required = true, description = "name", multiValued = false)
    String name;

    public void setCreditCardSource (CreditCardSource creditCardSource)
    {
        this.creditCardSource = creditCardSource;
    }

    @Override
    public Object execute (CommandSession session) throws Exception
    {
        CreditCard cc = creditCardSource.getCreditCardByNumber(name);
        String s = String.format("CreditCard:{id=%s, name=%s, number=%s}", cc.getId(), cc.getName(), cc.getNumber());
        System.out.println(s);

        return cc;
    }

}