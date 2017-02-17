package pluggable.scm.helpers;

import com.cloudbees.plugins.credentials.*;
import com.cloudbees.plugins.credentials.common.*;
import java.util.Properties;

/**
 * A helper class which contain helpful methods.
 */
public class CredentialsUtils {
    /**
     * Get user name and password from credentialsID.
     *
     * @param String credentials id.
     * @return String two values first is username, second password.
     * @throw IllegalArgumentException
     *            If credentialId is null or empty.
     */
    public static String[] extractPasswordCredentials(String credentialId){
        if(credentialId == null || credentialId.equals("")){
            throw new IllegalArgumentException("Credential id not valid.");
        }

        def username_matcher = CredentialsMatchers.withId(credentialId);

        def available_credentials = CredentialsProvider.lookupCredentials(
                StandardUsernameCredentials.class
        );

        return [CredentialsMatchers.firstOrNull(available_credentials, username_matcher).username,
                CredentialsMatchers.firstOrNull(available_credentials, username_matcher).password];
    }
}