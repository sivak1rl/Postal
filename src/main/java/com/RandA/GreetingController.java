package com.RandA;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@GetMapping("/")
	public String index(Model model) {

		return "index";
	}

	@PostMapping("/")
	public String handleUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");
		redirectAttributes.addFlashAttribute("file", file);
		System.out.println(file.getName() + ": File Name \r\n" + file.getSize() + ": size");
		Save.SaveImageToDb(file, file.getOriginalFilename());
		// System.out.println("file uploaded.");
		return "redirect:/PayDetails";
	}

	@GetMapping("/PayDetails")
	public String pay(Model model) {

		return "pay";
	}

	@PostMapping("/SubmitPayment")
	public String submitPay(Model model, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", "Your payment will be processed soon");
		try {
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", "localhost");
			Session sess = Session.getDefaultInstance(properties);
			MimeMessage mess = new MimeMessage(sess);
			mess.setFrom("sivak1rl@cmich.edu");
			mess.addRecipients(RecipientType.TO, "sivak1rl@cmich.edu");
			mess.setSubject("Test");
			mess.setText("Hello richard. you've won a prize!");

			Transport.send(mess);
		} catch (Exception e) {

		}
		return "redirect:/";
	}

}