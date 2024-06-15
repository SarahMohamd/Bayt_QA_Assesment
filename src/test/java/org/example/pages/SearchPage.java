<?php
        namespace org\example\pages;

        use Facebook\WebDriver\WebDriverBy;
        use PHPUnit\Framework\TestCase;
        use Facebook\WebDriver\Remote\RemoteWebDriver;
        use Facebook\WebDriver\WebDriverOptions;
        use Facebook\WebDriver\Firefox\FirefoxOptions;

class Search extends TestCase {
    private static $driver;

    public static function setUpBeforeClass(): void {
        $host = 'http://localhost:4444/wd/hub'; // Selenium server URL
        $capabilities = array('firefoxOptions' => array(
                'args' => array(
                '--width=390',
                '--height=844',
                '--disable-notifications'
        )
        ));

        $options = new FirefoxOptions($capabilities);
        self::$driver = RemoteWebDriver::create($host, $options);
        self::$driver->get('https://www.bayt.com/en/egypt/');
    }

    public function testSearch() {
        sleep(4); // Replace with appropriate wait strategies
        self::$driver->findElement(WebDriverBy::xpath("//div[@class='cky-notice-group']/div/button[@aria-label='Accept all cookies']"))->click();
        self::$driver->findElement(WebDriverBy::id("text_search"))->click();
        self::$driver->findElement(WebDriverBy::xpath("//div[@class='list-menu-title m']/div/input"))->sendKeys("Quality Assurance Engineer");
        sleep(4); // Replace with appropriate wait strategies
        self::$driver->findElement(WebDriverBy::xpath("//li[@data-text='quality assurance engineer']/a/span/span"))->click();
        self::$driver->findElement(WebDriverBy::id("search_country__r"))->click();
        self::$driver->findElement(WebDriverBy::xpath("//div[@class='list-menu-title ']/div[@class]/input"))->sendKeys("United Arab Emirates");
        sleep(4); // Replace with appropriate wait strategies
        self::$driver->findElement(WebDriverBy::xpath("//li[@class='is-active']/a"))->click();
        self::$driver->findElement(WebDriverBy::xpath("//button[@type='submit']"))->click();
    }

    public function testClickEasyApply() {
        sleep(4); // Replace with appropriate wait strategies
        $easyapplyBtns = self::$driver->findElements(WebDriverBy::linkText("Easy apply"));
        for ($i = 0; $i < count($easyapplyBtns); $i++) {
            $easyapplyBtns[$i]->click();
            if ($i == 1) {
                break;
            }
        }
        $this->assertTrue(self::$driver->findElement(WebDriverBy::id("JsApplicantRegisterForm_firstName"))->isDisplayed());
    }

    public static function tearDownAfterClass(): void {
        self::$driver->quit();
    }
}