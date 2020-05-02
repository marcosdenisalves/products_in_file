package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		List<Product> list = new ArrayList<>();

		System.out.print("Enter the file directory: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			String line = br.readLine();
			while (line != null) {
				String[] product = line.split(",");
				list.add(new Product(product[0], Double.parseDouble(product[1]), Integer.parseInt(product[2])));
				line = br.readLine();
			}
			String target = path.replace("\\Products.csv", "\\out");
			boolean file = new File(target).mkdir();

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(target + "\\Result.csv"))) {

				for (Product product : list) {
					bw.write(product.toString());
					bw.newLine();
				}
				if (file) {
					System.out.println("Sucessfully created file!");
				}else {
					System.out.println("Error creating file!");
				}
			}

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		sc.close();
	}
}
