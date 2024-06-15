<?php
        namespace org\example\pages;

        use Facebook\WebDriver\WebDriverBy;
        use Facebook\WebDriver\WebDriverExpectedCondition;
        use PHPUnit\Framework\TestCase;
        use org\example\pages\BaseTest;

class HomePage extends BaseTest {
    private $aboutUs = 'About Us';
    private $jobsApply = 'Jobs in UAE';
    private $easyApply = 'Easy apply';
    private $firstName = 'JsApplicantRegisterForm_firstName';
    private $lastName = 'JsApplicantRegisterForm_lastName';
    private $email = 'JsApplicantRegisterForm_email';
    private $password = 'JsApplicantRegisterForm_password';
    private $phone = 'JsApplicantRegisterForm_mobPhone';
    private $registerBtn = 'register';

    public function testClickAboutUsBtn() {
        $driver = self::$driver;
        $driver->executeScript('window.scrollBy(0,4500);');
        sleep(5);
        $driver->findElement(WebDriverBy::linkText($this->aboutUs))->click();
    }

    public function testApplyJob() {
        $driver = self::$driver;
        $driver->executeScript('window.scrollBy(0,2500);');
        sleep(5);
        $driver->findElement(WebDriverBy::linkText($this->jobsApply))->click();
    }

    public function testClickEasyApply() {
        $driver = self::$driver;
        $driver->executeScript('window.scrollBy(0,2000);');

        $easyapplyBtns = $driver->findElements(WebDriverBy::linkText($this->easyApply));
        foreach ($easyapplyBtns as $key => $btn) {
            $btn->click();
            if ($key == 1) {
                break;
            }
        }
    }

    public function testFillJobForm() {
        $driver = self::$driver;
        $driver->executeScript('window.scrollBy(0,2000);');

        $driver->findElement(WebDriverBy::id($this->firstName))->sendKeys('Sarah');
        $driver->findElement(WebDriverBy::id($this->lastName))->sendKeys('Mohamed');
        $driver->findElement(WebDriverBy::id($this->email))->sendKeys('saraabdelftahsos@gmail.com');
        $driver->findElement(WebDriverBy::id($this->password))->sendKeys('Sarah@123');
        $driver->findElement(WebDriverBy::id($this->phone))->sendKeys('1012194330');

        $driver->manage()->timeouts()->implicitlyWait(15);
        $driver->findElement(WebDriverBy::id($this->registerBtn))->click();

        $driver->executeScript('window.scrollBy(0,500);');
        $this->waitForElement(WebDriverBy::id('JsApplicantRegisterForm_email_em_'));

        $errorMsgAlreadyRegistered = $driver->findElement(WebDriverBy::id('JsApplicantRegisterForm_email_em_'))->getText();
        $this->assertEquals('This email is already registered.', $errorMsgAlreadyRegistered);
    }

    private function waitForElement($locator) {
        $driver = self::$driver;
        $wait = new \Facebook\WebDriver\WebDriverWait($driver, 10);
        $wait->until(WebDriverExpectedCondition::visibilityOfElementLocated($locator));
    }
}
