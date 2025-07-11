package com.rpm_store.Controller;

import com.rpm_store.DAO.VinileDAO;
import com.rpm_store.Model.Vinile;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/inventario")
public class InventarioServlet extends HttpServlet {

    private static final int VINILI_PER_PAGINA = 10;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        VinileDAO vinileDAO = new VinileDAO();
        String pagStr = request.getParameter("pagina");
        int pagina;
        if (pagStr == null)
            pagina = 1;
        else {
            pagina = Integer.parseInt(pagStr);
            if (pagina < 0) throw new MyServletException("Parametro pagina non valido", 400);
        }
        int offset = (pagina - 1) * VINILI_PER_PAGINA;
        List<Vinile> vinili = vinileDAO.doRetrieveAll(offset, VINILI_PER_PAGINA + 1);
        request.setAttribute("pagina", pagina);
        request.setAttribute("ultima", vinili.size() < VINILI_PER_PAGINA + 1);
        request.setAttribute("vinili", vinili.subList(0, Math.min(vinili.size(), VINILI_PER_PAGINA)));
        request.getRequestDispatcher("/WEB-INF/Pagine/Admin/inventario.jsp").forward(request, response);
    }
}