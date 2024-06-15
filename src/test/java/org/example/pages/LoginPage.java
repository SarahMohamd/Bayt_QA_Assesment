<?php
        namespace org\example\pages;

        use Facebook\WebDriver\WebDriverBy;
        use PHPUnit\Framework\TestCase;
        use org\example\pages\BaseTest;

class Login extends BaseTest {
    private $home;

    public function __construct() {
        parent::__construct();
        $this->home = new HomePage();
    }

    public function testLogin() {
        $driver = self::$driver;

        $driver->findElement(WebDriverBy::xpath("//a[@class='btn is-small is-inverse u-expanded-m ']"))->click();
        $driver->manage()->timeouts()->implicitlyWait(4);
        $driver->findElement(WebDriverBy::id("LoginForm_username"))->sendKeys("saraabdelftahsos@gmail.com");
        $driver->findElement(WebDriverBy::id("LoginForm_password"))->sendKeys("Sarah@123");
        $driver->findElement(WebDriverBy::id("login-button"))->click();

        $afterLoginText = $driver->findElement(WebDriverBy::xpath("//section[@class='form-section m0t'][1]"))->getText();
        $this->assertTrue(strpos($afterLoginText, "Tell us about yourself") !== false);
    }

    public function testDeleteAccount() {
        $driver = self::$driver;

        $driver->manage()->timeouts()->implicitlyWait(6);
        $driver->findElement(WebDriverBy::xpath("//li[@class='popover-owner'][4]"))->click();
        sleep(4);
        $driver->findElement(WebDriverBy::linkText("Account Settings"))->click();
        $driver->executeScript("window.scrollBy(0,1500)");
        sleep(4);
        $driver->findElement(WebDriverBy::xpath("//button[@data-cky-tag='accept-button']"))->click();
        $driver->findElement(WebDriverBy::linkText("Delete My Account"))->click();
        $driver->manage()->timeouts()->implicitlyWait(4);
        $driver->findElement(WebDriverBy::className("btn is-danger"))->click();
        $driver->manage()->timeouts()->implicitlyWait(4);
        $driver->findElement(WebDriverBy::xpath("//button[@class='btn u-expanded-m  is-danger non-aid']"))->click();

        $webElementText = $driver->findElement(WebDriverBy::linkText("For Employers"))->getText();
        $this->assertEquals("For Employers", $webElementText);
    }
}
