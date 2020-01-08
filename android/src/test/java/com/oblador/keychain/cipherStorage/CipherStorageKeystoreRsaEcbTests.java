package com.oblador.keychain.cipherStorage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;

import androidx.biometric.BiometricManager;

import com.facebook.react.bridge.ReactApplicationContext;
import com.oblador.keychain.FakeProvider;
import com.oblador.keychain.SecurityLevel;

import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.VerificationCollector;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.security.Key;
import java.security.Security;

import javax.crypto.SecretKey;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.robolectric.Shadows.shadowOf;

@RunWith(RobolectricTestRunner.class)
public class CipherStorageKeystoreRsaEcbTests {
  /** Cancel test after 5 seconds. */
  @ClassRule
  public static Timeout timeout = Timeout.seconds(10);
  /** Get test method name. */
  @Rule
  public TestName methodName = new TestName();
  /** Mock all the dependencies. */
  @Rule
  public MockitoRule mockDependencies = MockitoJUnit.rule().silent();
  @Rule
  public VerificationCollector collector = MockitoJUnit.collector();

  private FakeProvider provider = new FakeProvider();

  @Before
  public void setUp() throws Exception {
    Security.insertProviderAt(provider, 0);
  }

  @After
  public void tearDown() throws Exception {
    Security.removeProvider(FakeProvider.NAME);
  }

  private ReactApplicationContext getRNContext() {
    return new ReactApplicationContext(RuntimeEnvironment.application);
  }
}
