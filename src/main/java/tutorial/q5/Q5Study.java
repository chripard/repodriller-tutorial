package tutorial.q5;

import br.com.metricminer2.RepositoryMining;
import br.com.metricminer2.Study;
import br.com.metricminer2.persistence.csv.CSVFile;
import br.com.metricminer2.scm.GitRepository;
import br.com.metricminer2.scm.commitrange.Commits;

public class Q5Study implements Study {

	@Override
	public void execute() {

		new RepositoryMining()
		.in(GitRepository.singleProject("/Users/mauricioaniche/workspace/metricminer2-tutorial/project/jfreechart"))
		.through(Commits.monthly(6))
		.process(new CCVisitor(), new CSVFile("/Users/mauricioaniche/workspace/metricminer2-tutorial/project/q5.csv"))
		.mine();
	}

}
