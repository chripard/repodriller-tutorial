package tutorial.q3;

import java.util.Hashtable;
import java.util.Map;

import br.com.metricminer2.domain.Commit;
import br.com.metricminer2.domain.Modification;
import br.com.metricminer2.domain.ModificationType;
import br.com.metricminer2.persistence.PersistenceMechanism;
import br.com.metricminer2.scm.CommitVisitor;
import br.com.metricminer2.scm.SCMRepository;

public class ModificationsPerFileVisitor implements CommitVisitor {

	private Map<String, Integer> files;
	
	public ModificationsPerFileVisitor() {
		this.files = new Hashtable<String, Integer>();
	}
	
	@Override
	public void process(SCMRepository repo, Commit commit, PersistenceMechanism writer) {

		for(Modification m : commit.getModifications()) {
			if(m.wasDeleted()) continue;
			
			else if(m.getType() == ModificationType.RENAME) {
				Integer total = files.get(m.getOldPath());
				files.remove(m.getOldPath());
				files.put(m.getNewPath(), total);
			}
			
			plusOne(m.getNewPath());
		}
	}
	
	public Map<String, Integer> getFiles() {
		return files;
	}

	private void plusOne(String file) {
		
		if(!files.containsKey(file))
			files.put(file, 0);
		
		Integer currentQty = files.get(file);
		files.put(file, currentQty + 1);
		
	}

	@Override
	public String name() {
		return "modifications-per-file";
	}

}
