package tutorial.q6;

import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.domain.Modification;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.SCMRepository;

public class DiffVisitor implements CommitVisitor {

	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {
		int adds = 0;
		int removes = 0;
		
		for(Modification m : commit.getModifications()) {
			String diff = m.getDiff();
			String[] lines = diff.split("\n");
			
			for(String line : lines) {
				if(line.trim().startsWith("+")) adds++;
				else if(line.trim().startsWith("-")) removes++;
			}
			
			writer.write(
				commit.getHash(),
				adds,
				removes
			);
			
		}
		
	}

	@Override
	public String name() {
		return "diff";
	}

}
