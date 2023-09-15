package it.academy.jd2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/singer-genres")
public class SingerPostServlet extends HttpServlet{

    Map<String, Integer> singerMap = new HashMap<>();

    Map<String, Integer> genresMap = new HashMap<>();

    List<String> textCommentList = new ArrayList<>();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        writer.write("<p>Best singer - " + singerMap + "</p>" );
        writer.write("<p>Best genres - " + genresMap + "</p>");
        writer.write("<p>Last comment:  "+ textCommentList  + "</p>");

        writer.write("<t1>" + "GET" + "/t1");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        String singerVal = req.getParameter("singer");

        if (singerVal != null) {
            if (singerMap.containsKey(singerVal)) {
                int x = singerMap.get(singerVal).intValue();
                x += 1;
                singerMap.put(singerVal, x);
            } else singerMap.put(singerVal, 1);
        }

        String singerMaxKey = null;
        for (String key : singerMap.keySet()) {
            if (singerMaxKey == null || singerMap.get(key) > singerMap.get(singerMaxKey)) {
                singerMaxKey = String.valueOf(key);
            }
        }

        String genresMaxKey = null;
        for(String key : genresMap.keySet()){
            if(genresMaxKey == null || genresMap.get(key) > genresMap.get(genresMaxKey)){
                genresMaxKey = String.valueOf(key);
            }
        }



        String [] genresArr = req.getParameterValues("genres");

        for (String s: genresArr) {
            if (singerVal != null) {
                if (genresMap.containsKey(s)) {
                    int x = genresMap.get(s).intValue();
                    x += 1;
                    genresMap.put(s, x);
                } else genresMap.put(s, 1);
            }
        }


        String textComment = req.getParameter("message");
        if (textComment != "") {
            textCommentList.add(textComment);
        }

        writer.println("<html>");
        writer.write("<head>");
        writer.write("<title>info</title>");
        writer.write("</head>");
        writer.write("<body>");
        writer.write("<h1>VOTE</h1>");
        writer.write("<p>Best singer - " + singerMaxKey + "</p>");
        writer.write("<p>Best genres - " + genresMaxKey + "</p>");
        writer.write("<p>Last comment:  "+ textCommentList.get(textCommentList.size() - 1) + "</p>");
        writer.write("</body>");
    }

}
