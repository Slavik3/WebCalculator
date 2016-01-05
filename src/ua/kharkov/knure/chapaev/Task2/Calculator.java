package ua.kharkov.knure.chapaev.Task2;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * test
 */
public class Calculator extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	double result = 0;
	
	private double calculate(double param1, double param2, short operation) {
		switch (operation) {
		case 1:
			return param1 + param2;
		case 2:
			return param1 - param2;
		case 3:
			return param1 * param2;
		case 4:
			return param1 / param2;
		default:
			return 0;
		}
	}

	private short getOperationID(String operation) {
		switch (operation) {
		case "+":
			return 1;
		case "-":
			return 2;
		case "*":
			return 3;
		case "/":
			return 4;
		default:
			return 0;
		}
	}

	private boolean isValid(String number) {
		return number.matches("^[0-9]+$");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("index.jsp");
		if (request.getParameter("calculate") != null) {
			String param1 = request.getParameter("param1");
			String param2 = request.getParameter("param2");
	
			double firstParam = (isValid(param1)) ? Double.parseDouble(param1) : 0;
			double secondParam = (isValid(param2)) ? Double.parseDouble(param2) : 0;

			String operation = request.getParameter("operation");

			result = calculate(firstParam, secondParam, getOperationID(operation));
			
			request.setAttribute("result", result);
			
			System.out.println("Result --> " + result);
		}
		view.forward(request, response);
	}
	
}
