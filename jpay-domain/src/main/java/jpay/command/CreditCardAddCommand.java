package jpay.command;

import jpay.domain.CreditCardSource;
import jpay.model.CreditCard;
import org.apache.felix.gogo.commands.Action;
import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.felix.service.command.CommandSession;

/**
 * CreditCardAddCommand
 */
@Command(scope = "creditcard", name = "add", description = "Add CreditCard")
public class CreditCardAddCommand implements Action
{
    private CreditCardSource creditCardSource;

    @Argument(index = 0, name = "name", required = true, description = "name", multiValued = false)
    String name;

    @Argument(index = 1, name = "number", required = true, description = "number", multiValued = false)
    String number;

    public void setCreditCardSource (CreditCardSource creditCardSource)
    {
        this.creditCardSource = creditCardSource;
    }

    @Override
    public Object execute (CommandSession session) throws Exception
    {
        CreditCard cc = new CreditCard(name, number);
        creditCardSource.addCreditCard(cc);
        return cc;
    }

}



