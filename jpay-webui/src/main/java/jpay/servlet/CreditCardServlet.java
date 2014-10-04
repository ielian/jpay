package jpay.servlet;

import jpay.model.CreditCard;
import jpay.service.CreditCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class CreditCardServlet extends HttpServlet
{
    private Logger logger = LoggerFactory.getLogger(CreditCardServlet.class);

    @Inject
    private CreditCardService creditCardService;

/*
    @Override
    public void init (ServletConfig config) throws ServletException
    {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }
*/

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ServletOutputStream os = resp.getOutputStream();
        Collection<CreditCard> cards = creditCardService.getCreditCardByName("user");
        os.println("<html><body>");
        os.println("<h2>Cards</h2>");
        os.println("<table>");
        os.println("<tr><th>Id</th><th>Name</th><th>URL</th></tr>");
        for (CreditCard cc : cards) {
            String name = (cc.getName() == null) ? "" : cc.getName();
            os.println(String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>", cc.getId(), cc.getName(), cc.getNumber()));
        }
        os.println("</table>");
        os.println("<h2>Add CreditCard</h2>");
        os.println("<form name='input' action='/webui' method='post'>");
        os.println("<table>");
        //os.println("<tr><td>Id</td><td><input type='text' name='id'/></td></tr>");
        os.println("<tr><td>Tag</td><td><input type='text' name='name'/></td></tr>");
        os.println("<tr><td>Type</td><td><input type='text' name='number'/></td></tr>");
        os.println("<tr><td colspan='2'><input type='submit' value='Add'/></td></tr>");
        os.println("</form>");
        os.println("</table>");
        os.println("</body></html>");
    }

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String number = req.getParameter("number");
        CreditCard cc = new CreditCard(name, number);
        creditCardService.addCreditCard(cc);
        resp.sendRedirect("/webui");
    }

    public void setCreditCardService (CreditCardService creditCardService)
    {
        //System.setProperty("http.proxyHost", "");
        //System.setProperty("http.proxyPort", "");
        this.creditCardService = creditCardService;
        logger.info("CreditCardServiceProxy=" + creditCardService);
    }


    public CreditCardService getCreditCardService ()
    {
        return this.creditCardService;
    }
}
