package zeebe.camunda.worker;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProvider;
import io.camunda.zeebe.client.impl.oauth.OAuthCredentialsProviderBuilder;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariable;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
@EnableZeebeClient
@RestController
public class WorkerApplication {
	static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	static ConsoleHandler handler = new ConsoleHandler();

	public static void main(String[] args) {
		SpringApplication.run(WorkerApplication.class, args);
		logger.setLevel(Level.ALL);
		handler.setLevel(Level.ALL);
		logger.addHandler(handler);
	}

	@ZeebeWorker(type = "Register_an_account", autoComplete = true)
	public void registerAnAccount(final JobClient client, final ActivatedJob job, @ZeebeVariable String person_uuid) {
		logger.info("Register_an_account " + person_uuid + " from website");
		logger.info(" create Successfully. " + person_uuid );
	}

	@ZeebeWorker(type = "Pick_the_favorite_item")
	public void PickTheFavoriteItem(final JobClient client, final ActivatedJob job, @ZeebeVariable String person_uuid) {
		UUID recovery_certificate_uuid = UUID.randomUUID();
		logger.info("Pick the favorite item " + person_uuid +"...");
		logger.info("Pick the favorite item: " + recovery_certificate_uuid);

		client.newCompleteCommand(job.getKey())
				.variables("{\"Pick_the_favorite_item\": \"" + recovery_certificate_uuid + "\"}")
				.send()
				.exceptionally( throwable -> { throw new RuntimeException("Could not complete job " + job, throwable); });
	}

	@ZeebeWorker(type = "Change_Oder", autoComplete = true)
	public void sendCertificateOfRecovery(final JobClient client, final ActivatedJob job, @ZeebeVariable String person_uuid, @ZeebeVariable String recovery_certificate_uuid) {
		logger.info("Change Oder" + recovery_certificate_uuid + " from external database...");
		logger.info("Change Oder " + person_uuid );
		}

	@ZeebeWorker(type = "Make_a_Pament", autoComplete = true)
	public void MakeAPament(final JobClient client, final ActivatedJob job, @ZeebeVariable String person_uuid) {
		logger.info("Make a Pament " + person_uuid + " from website");
		logger.info(" Make a Pament Successfully. " + person_uuid );
	}
	@ZeebeWorker(type = "View_the_oder_informationView_the_oder_information", autoComplete = true)
	public void ViewTheOderInformationViewTheOderInformation(final JobClient client, final ActivatedJob job, @ZeebeVariable String person_uuid) {
		logger.info("View the oder information " + person_uuid + " from website");
		logger.info(" View the oder informationView the oder information Successfully. " + person_uuid );
	}
	@ZeebeWorker(type = "receive_theitem", autoComplete = true)
	public void ReceiveTheitemreceiveTheitem(final JobClient client, final ActivatedJob job, @ZeebeVariable String person_uuid) {
		logger.info("receive_theitem " + person_uuid + " from website");
		logger.info(" receive_theitemreceive_theitem  Successfully. " + person_uuid );
	}

	@ZeebeWorker(type = "Complain_to_customer_serviceComplain_to_customer_service", autoComplete = true)
	public void ComplainToCustomerServiceComplainToCustomerService(final JobClient client, final ActivatedJob job, @ZeebeVariable String person_uuid) {
		logger.info("Complain_to_customer_service " + person_uuid + " from website");
		logger.info(" Complain_to_customer_serviceComplain_to_customer_service  Successfully. " + person_uuid );
	}

}
