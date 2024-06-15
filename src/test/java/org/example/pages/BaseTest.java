

    <?php
    namespace org\example\pages;

    use Facebook\WebDriver\Remote\RemoteWebDriver;
    use Facebook\WebDriver\WebDriverBy;
    use Facebook\WebDriver\WebDriverOptions;
    use Facebook\WebDriver\WebDriverWindow;

    class BaseTest {
        public static $driver;

        public function setUp() {
            $host = 'http://localhost:4444/wd/hub'; // Selenium server URL
            $capabilities = ['browserName' => 'firefox']; // Browser capabilities

            self::$driver = RemoteWebDriver::create($host, $capabilities);
            self::$driver->manage()->window()->maximize();
            self::$driver->get('https://www.bayt.com/en/egypt/');
        }
    }

