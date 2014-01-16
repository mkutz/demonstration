package de.assertagile.demonstration.howtotest.web

import geb.report.ScreenshotReporter
import geb.spock.GebReportingSpec
import org.sikuli.core.search.ImageSearcher
import org.sikuli.core.search.Match

import java.awt.image.BufferedImage

/**
 * Abstract GebReportingSpec using Geb's {@link geb.Browser#report(String)} and Sikuli to analyse the current page.
 */
abstract class SikuliGebReportingSpec extends GebReportingSpec {

    static final LABEL = "sikuli"

    def setupSpec() {
        ImageLocator.addImagePathFirst("src/test/resources")
    }

    /**
     * Uses Sikuli's {@link Finder#findAll(java.lang.String)} to find all occurrences of an image on the current page.
     *
     * @param imagePath the path of the image that should be found on the current page (relative to src/test/resources).
     * @param minSimilarity the minimum similarity of a match as double. Defaults to 0.9.
     * @return a {@link List} of {@link Match}es of the image found at the given imagePath.
     */
    protected List<Match> getAllImageMatches(String imagePath, double minSimilarity = 0.9) {
        ImageSearcher imageSearcher = new ImageSearcher(new BufferedImage())
        Finder finder = null
        try {
            finder = new Finder(takeScreenshot())
            finder.findAll(imagePath, minSimilarity)
            return finder.toList()
        } finally {
            finder.destroy()
        }
    }

    /**
     * Uses Sikuli's {@link Finder#find(java.lang.String)} to determine if an image can be found on the current page.
     *
     * @param imagePath the path of the image that should be found on the current page (relative to src/test/resources).
     * @param minSimilarity the minimum similarity of a match as double. Defaults to 0.9.
     * @return {@code true} if the images can be found on the current page <em>or</em> if the current driver does not
     *      support taking screenshots.
     */
    protected boolean isImageOnPage(String imagePath, double minSimilarity = 0.9) {
        Finder finder = null
        try {
            finder = new Finder(takeScreenshot())
            finder.find(imagePath, minSimilarity)
            return finder.hasNext()
        } catch (IllegalStateException e) {
            return true
        } finally {
            finder.destroy()
        }
    }

    /**
     * Uses Sikuli's {@link Finder} to determine if a {@link List} of images can be found on the current page.
     *
     * @param imagePaths the paths of the images that should be found on the current page (relative to
     *      src/test/resources).
     * @param minSimilarity the minimum similarity of a match as double. Defaults to 0.9.
     * @return {@code true} if the image can be found on the current page <em>or</em> if the current driver does not
     *      support taking screenshots.
     */
    protected boolean areImagesOnPage(List<String> imagePaths, double minSimilarity = 0.9) {
        Finder finder = null
        List<String> found = []

        try {
            finder = new Finder(takeScreenshot())
            imagePaths.each { String imagePath ->
                finder.find(imagePath, minSimilarity)
                if (finder.hasNext()) found << imagePath
            }
            assert found == imagePaths
        } finally {
            finder.destroy()
        }
        return true
    }

    /**
     * Uses {@link geb.Browser#report(String)} to take a screenshot of the current page and returns its absolute path.
     *
     * @return the absolute path of the just taken screenshot.
     * @throw IllegalStateException if the current driver does not support taking screenshots.
     */
    private String takeScreenshot() {
        if (config.reporter instanceof ScreenshotReporter) {
            throw IllegalStateException("The current browser ${browser.driver} does not support taking screenshots.")
        }
        report(LABEL)
        sleep(200)
        browser.getReportGroupDir().listFiles().toList().sort { File file -> file.lastModified() }[-1].absolutePath
    }
}