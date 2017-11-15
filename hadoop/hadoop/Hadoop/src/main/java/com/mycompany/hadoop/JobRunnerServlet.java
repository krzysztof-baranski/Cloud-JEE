package com.mycompany.hadoop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.util.ToolRunner;

@WebServlet("/start")

public class JobRunnerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String inputFile = "D:\\Cloud JEE\\hadoop\\hadoop\\Hadoop\\password.txt";
        String[] params = new String[]{
            "/user/vagrant/input/password.txt", // gdzie zapisze hasła na ubuntu
            "/user/vagrant/output", // gdzie zapisze wynik na ubuntu
            inputFile // plik wejsciowy z windowsa
        }; 
        try {
            int tool = ToolRunner.run(new HadoopConfig(), new ToolRunnerClass(), params);
            PrintWriter out = response.getWriter();
            
            out.println(tool == 0 ? "Success" : "Error");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
