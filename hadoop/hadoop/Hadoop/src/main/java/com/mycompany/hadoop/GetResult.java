package com.mycompany.hadoop;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

@WebServlet("/getresult")

public class GetResult extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Configuration conf = new HadoopConfig();
        FileSystem fs = FileSystem.get(conf);
        Scanner s = new Scanner(fs.open(new Path("/user/vagrant/output/part-r-00000")));
        
        StringBuilder sb = new StringBuilder();
        sb.append(s.nextLine());
        PrintWriter out = response.getWriter();
        try {
            while (s.hasNextLine()) {
                sb.append("\n").append(s.nextLine());
            }
            fs.close();
            s.close();
            out.println(sb.toString());
            out.close();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
