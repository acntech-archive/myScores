package myscores.security;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PasswordTest {

    @Test
    public void testEncryptPassword() {
        String secret = "some-secret-string";
        Password password = new Password();
        String salt = password.createSalt();
        String hash = password.encryptPassword(secret, salt);

        assertNotEquals(password, salt);
        assertNotEquals(password, hash);
        assertTrue(password.verifyPassword(secret, salt, hash));
    }

    @Test
    public void testEncryptPasswordTwiceWithSameSalt() {
        String secret = "some-secret-string";
        Password password = new Password();
        String salt = password.createSalt();
        String hash1 = password.encryptPassword(secret, salt);
        String hash2 = password.encryptPassword(secret, salt);

        assertEquals(hash1, hash2);
        assertTrue(password.verifyPassword(secret, salt, hash1));
        assertTrue(password.verifyPassword(secret, salt, hash2));
    }

    @Test
    public void testEncryptPasswordTwiceWithDifferntSalts() {
        String secret = "some-other-secret-string";
        Password password = new Password();
        String salt1 = password.createSalt();
        String hash1 = password.encryptPassword(secret, salt1);
        String salt2 = password.createSalt();
        String hash2 = password.encryptPassword(secret, salt2);

        assertNotEquals(salt1, salt2);
        assertNotEquals(hash1, hash2);
        assertTrue(password.verifyPassword(secret, salt1, hash1));
        assertTrue(password.verifyPassword(secret, salt2, hash2));
        assertFalse(password.verifyPassword(secret, salt1, hash2));
        assertFalse(password.verifyPassword(secret, salt2, hash1));
    }

    @Test
    public void testEncryptTwoPasswordsWithSameSalts() {
        String secret1 = "first-secret-string";
        String secret2 = "second-secret-string";
        Password password = new Password();
        String salt = password.createSalt();
        String hash1 = password.encryptPassword(secret1, salt);
        String hash2 = password.encryptPassword(secret2, salt);

        assertNotEquals(secret1, secret2);
        assertNotEquals(hash1, hash2);
        assertTrue(password.verifyPassword(secret1, salt, hash1));
        assertTrue(password.verifyPassword(secret2, salt, hash2));
        assertFalse(password.verifyPassword(secret1, salt, hash2));
        assertFalse(password.verifyPassword(secret2, salt, hash1));
    }
}
