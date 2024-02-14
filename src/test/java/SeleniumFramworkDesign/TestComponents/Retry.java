package SeleniumFramworkDesign.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {

	int count = 0;
	int maxRetry = 1;

	@Override
	public boolean retry(ITestResult result) {

		// result has all the particular test metadata
		if (count < maxRetry) {
			count++;
			return true;
			// here with return true we are saying to rerun the retry method so that the
			// failed test will be re-run one time
		}
		return false;
	}

}
