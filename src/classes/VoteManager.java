package classes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VoteManager {
	private Calendar startDate;
	private Calendar endDate;
	private List<String> candidateList = new ArrayList<>();
	private boolean isVotingResultShown = false;
	
	void showResult() {
		isVotingResultShown = true;
	}
}
