package c23_99_m_webapp.backend;

import c23_99_m_webapp.backend.models.enums.Role;
import c23_99_m_webapp.backend.models.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mostrarRol();
	}

	// Método para probar la asignación de roles
	public void mostrarRol() {
		User user = new User();

		// Probar diferentes roles
		user.setRole(Role.USER);
		System.out.println(user.getRole() + ": " + user.getAuthorities());

		user.setRole(Role.ADMIN);
		System.out.println(user.getRole() + ": " + user.getAuthorities());

		user.setRole(Role.TEACHER);
		System.out.println(user.getRole() + ": " + user.getAuthorities());

		user.setRole(Role.SUPERADMIN);
		System.out.println(user.getRole() + ": " + user.getAuthorities());
	}
}
