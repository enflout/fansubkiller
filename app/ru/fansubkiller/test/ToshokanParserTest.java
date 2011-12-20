package ru.fansubkiller.test;

import org.junit.Before;
import org.junit.Test;
import ru.fansubkiller.content.SearchResult;
import ru.fansubkiller.search.ToshokanParser;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author i.orlov
 */
public class ToshokanParserTest {
  private ToshokanParser parser;

  @Before
  public void setUp() {
    parser = new ToshokanParser();
  }

  @Test
  public void testGetResults() {
    String readWebPage =
        "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n" +
            "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\n" +
            "<head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />\n" +
            "<title>Tokyo Toshokan :: #tokyotosho @ irc.rizon.net :: Torrent Listing</title><link rel=\"stylesheet\" type=\"text/css\" href=\"http://s.tokyotosho.info/css7.php\" title=\"Default\" />\n" +
            "\t\t<link rel=\"alternate stylesheet\" type=\"text/css\" href=\"http://s.tokyotosho.info/css_inverse.php\" title=\"Inverse\" />\n" +
            "\t<link rel=\"icon\" type=\"image/png\" href=\"http://s.tokyotosho.info/favicon.png\" />\n" +
            "\t\n" +
            "\t<link rel=\"shortcut icon\" href=\"http://tokyotosho.info/favicon.ico\" /><link rel=\"alternate\" title=\"Tokyo Toshokan RSS\" href=\"rss.php\" type=\"application/rss+xml\" />\n" +
            "\t<link rel=\"search\" type=\"application/opensearchdescription+xml\" title=\"Tokyo Toshokan\" href=\"/tokyotosho-search.php\" /></head><body><script type=\"text/javascript\" src=\"http://s.tokyotosho.info//styleswitcher.js\"></script><div id=\"main\">\n" +
            "<h1>Tokyo <span title=\"Japanese: Libary\">Toshokan</span></h1>\n" +
            "\n" +
            "<div class=\"centertext\">東京 図書館</div>\n" +
            "<h2><a class=\"h2\" style=\"font-size: 9pt\" href=\"irc://irc.rizon.net/tokyotosho\">#tokyotosho @ irc.rizon.net</a><br />\n" +
            "<span style=\"font-size: 8pt\"><a href=\"http://tokyotosho.info\">http://tokyotosho.info</a> <a href=\"http://tokyotosho.se\">http://tokyotosho.se</a> <a href=\"http://tokyo-tosho.net\">http://tokyo-tosho.net</a></span>\n" +
            "</h2>\n" +
            "<h3>A BitTorrent <span title=\"English: Toshokan\">Library</span> for Japanese Media</h3>\n" +
            "\n" +
            "<ul class=\"menuwrapper\">\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"Lost?\" href=\"index.php\">Home</a> (<a class=\"head\" href=\"https://www.tokyotosho.info/\">SSL</a>) ::</li>\n" +
            "<li class=\"menu\"><a class=\"head\" href=\"settings.php\">Customize</a> ::</li>\n" +
            "<li class=\"menu\"><a class=\"head\" href=\"login.php\">Register/Login</a> ::</li>\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"Tell us about a torrent we don't know about\" href=\"new.php\">Submit New Torrent</a> ::</li>\n" +
            "\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"We probably have what you're looking for!\" href=\"search.php\">Search</a> ::</li>\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"Keep on top of the releases\" href=\"rss.php\">RSS</a> (<a class=\"head\" title=\"Or just some of the releases\" href=\"rss_customize.php\">Customize</a>)<br /></li>\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"Post messages in me!\" href=\"http://forums.tokyotosho.info/\">Forums</a> ::</li>\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"Post messages in me!\" href=\"/trac\">Trac/Wiki</a> ::</li>\n" +
            "\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"Chat or idle with us on IRC\" href=\"irc://irc.rizon.net/tokyotosho\">Chat</a> ::</li>\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"Did we mess something up?\" href=\"/trac/report\">Bug Reports</a> ::</li>\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"It's teh moniez\" href=\"/donate.php\">Donate!</a> ::</li>\n" +
            "<li class=\"menu\"><a class=\"head\" title=\"Let's hear from you!\" href=\"/contact.php\">Contact</a> ::</li>\n" +
            "<li class=\"menu\"><strong><a class=\"head\" href=\"https://www.privateinternetaccess.com/pages/protect-tt/\">VPN Service</a></strong> ::</li>\n" +
            "\n" +
            "<li class=\"menu\"><a class=\"head\" href=\"http://idlerpg.tokyotosho.info\">IdleRPG</a></li>\n" +
            "</ul>\n" +
            "<p class=\"centertext\"><acronym title=\"Coordinated Universal Time\">(UTC)</acronym> 2011-11-19 18:16 <acronym title=\"Central Standard (UTC -6h) or Daylight Saving Time (GMT -5h)\">(Central US)</acronym> 2011-11-19 12:16 <acronym title=\"UTC +9h\">(Japan)</acronym> 2011-11-20 03:16</p>\n" +
            "<ul class=\"news\">\n" +
            "<li><b>2011-07-17</b>\n" +
            "The <a href=\"/search.php\">search page</a> now supports pages!\n" +
            "\n" +
            "<br />\n" +
            "<br />\n" +
            "</li>\n" +
            "<li style=\"text-align: left\"><b>2007-10-25</b>\n" +
            "\t<a href=\"http://forums.tokyotosho.info/index.php/topic,2138.0.html\">Requesting comments</a> for TTv3. Post your suggestions!\n" +
            "</li>\n" +
            "</ul>\n" +
            "<form action=\"search.php\" method=\"get\" accept-charset=\"utf-8\">\n" +
            "\t<table cellspacing=\"0\" cellpadding=\"0\" style=\"width: 100%; padding-bottom: 1em; margin: 0;\">\n" +
            "<tr><td colspan=\"3\"><div class=\"dcenter\">75 Most recent torrents (All)<br /><input size=\"60\" type=\"text\" name=\"terms\" />\n" +
            "    <select name=\"type\">\n" +
            "\n" +
            "\t<option value=\"0\">All</option><option value=\"1\">Anime</option><option value=\"10\">Non-English</option><option value=\"8\">Drama</option><option value=\"3\">Manga</option><option value=\"2\">Music</option><option value=\"9\">Music Video</option><option value=\"7\">Raws</option><option value=\"4\">Hentai</option><option value=\"12\">Hentai (Anime)</option><option value=\"13\">Hentai (Manga)</option><option value=\"14\">Hentai (Games)</option><option value=\"11\">Batch</option><option value=\"5\">Other</option></select>\n" +
            "<br />\n" +
            "\n" +
            "Torrent Size (Min MB):\n" +
            "<input size=\"4\" type=\"text\" name=\"size_min\" value=\"\" />\n" +
            "(Max MB):\n" +
            "<input size=\"4\" type=\"text\" name=\"size_max\" value=\"\" /><br />\n" +
            "Submitter:\n" +
            "<input size=\"12\" type=\"text\" name=\"username\" value=\"\" />\n" +
            "\n" +
            "    <input type=\"submit\" value=\"Search\" /><br />All <a href=\"?cat=1\">Anime</a> <a href=\"?cat=10\">Non-English</a> <a href=\"?cat=8\">Drama</a> <a href=\"?cat=3\">Manga</a> <a href=\"?cat=2\">Music</a> <a href=\"?cat=9\">Music Video</a> <a href=\"?cat=7\">Raws</a> <a href=\"?cat=4\">Hentai</a> <a href=\"?cat=12\">Hentai (Anime)</a> <a href=\"?cat=13\">Hentai (Manga)</a> <a href=\"?cat=14\">Hentai (Games)</a> <a href=\"?cat=11\">Batch</a> <a href=\"?cat=5\">Other</a> <br /><strong>Top Searches</strong><br />\n" +
            "\n" +
            " <a href=\"search.php?terms=fate\">fate</a> (335) <a href=\"search.php?terms=Working\">Working</a> (230) <a href=\"search.php?terms=%ED%95%9C%EC%83%9B\">한샛</a> (148) <a href=\"search.php?terms=zip\">zip</a> (127) <a href=\"search.php?terms=fate+zero\">fate zero</a> (119) <a href=\"search.php?terms=one+piece\">one piece</a> (82)<br /> <a href=\"search.php?terms=c\">c</a> (82) <a href=\"search.php?terms=gundam\">gundam</a> (81) <a href=\"search.php?terms=c80\">c80</a> (72) <a href=\"search.php?terms=zero\">zero</a> (50)<br /><br /></div></td></tr>\n" +
            "\n" +
            "<tr class=\"category_1 shade\"><td rowspan=\"2\"><a href=\"?cat=1\"><img class=\"icon\" alt=\"Anime\" src=\"http://s.tokyotosho.info/cat_anime.png\" /></a></td><td class=\"desc-top\"><a rel=\"nofollow\" type=\"application/x-bittorrent\" href=\"http://www.nyaa.eu/?page=download&amp;tid=261245\">[Tsuki-Hatsuyuki]_Guilty_Crown_-_06_[704x400][8EE80484].avi</a></td><td class=\"web\"><a rel=\"nofollow\" href=\"http://tsuki-subs.info/\">Website</a> | <a href=\"details.php?id=471175\">Details</a></td></tr><tr class=\"category_1 shade\"><td class=\"desc-bot\">Submitter: <a href=\"/search.php?username=tsukisubs\">tsukisubs</a> | Size: 174.26MB | Date: 2011-11-19 15:55 UTC | Comment: <a class=\"irc\" href=\"irc://irc.rizon.net/tsuki-subs\">#tsuki-subs@irc.rizon.net</a> | <a class=\"irc\" href=\"irc://irc.rizon.net/hatsuyuki\">#hatsuyuki@irc.rizon.net</a> |We need AFX logo maker to help us. Please contact us at tsukisubs@gmail.com</td><td align=\"right\" style=\"color: #BBB; font-family: monospace\" class=\"stats\">S: <span style=\"color: green\">52</span> L: <span style=\"color: green\">34</span> C: <span style=\"color: green\">10</span> ID: 471175</td></tr>\n" +
            "\n" +
            "<tr class=\"category_1\"><td rowspan=\"2\"><a href=\"?cat=1\"><img class=\"icon\" alt=\"Anime\" src=\"http://s.tokyotosho.info/cat_anime.png\" /></a></td><td class=\"desc-top\"><a rel=\"nofollow\" type=\"application/x-bittorrent\" href=\"http://www.nyaa.eu/?page=download&amp;tid=261244\">[Tsuki-Hatsuyuki]_Guilty_Crown_-_05v2_[704x400][9BAB2393].avi</a></td><td class=\"web\"><a rel=\"nofollow\" href=\"http://tsuki-subs.info/\">Website</a> | <a href=\"details.php?id=471174\">Details</a></td></tr><tr class=\"category_1\"><td class=\"desc-bot\">Submitter: <a href=\"/search.php?username=tsukisubs\">tsukisubs</a> | Size: 174.19MB | Date: 2011-11-19 15:54 UTC | Comment: <a class=\"irc\" href=\"irc://irc.rizon.net/tsuki-subs\">#tsuki-subs@irc.rizon.net</a> | <a class=\"irc\" href=\"irc://irc.rizon.net/hatsuyuki\">#hatsuyuki@irc.rizon.net</a> |We need AFX logo maker to help us. Please contact us at tsukisubs@gmail.com</td><td align=\"right\" style=\"color: #BBB; font-family: monospace\" class=\"stats\">S: <span style=\"color: green\">28</span> L: <span style=\"color: green\">32</span> C: <span style=\"color: green\">10</span> ID: 471174</td></tr>\n" +
            "\n" +
            "<tr><td style=\"width: 100%\" colspan=\"4\"><br /></td></tr></table><table cellspacing=\"0\" cellpadding=\"0\" style=\"width: 100%\"><tr><td class=\"nav\" style=\"width: 33%; text-align: right\"><a href=\"?page=1&amp;cat=0\">Next 75 &gt;&gt;</a></td></tr></table></form>\n" +
            "<form action=\"search.php\" method=\"get\" accept-charset=\"utf-8\">\n" +
            "<table class=\"centertext\" cellspacing=\"0\" cellpadding=\"0\" style=\"width: 100%; padding-bottom: 1em; margin: 0;\">\n" +
            "<tr><td>\n" +
            "<input size=\"60\" type=\"text\" name=\"terms\" /><select name=\"type\">\n" +
            "<option value=\"0\">All</option>\n" +
            "<option value=\"1\">Anime</option>\n" +
            "<option value=\"10\">Non-English</option>\n" +
            "<option value=\"8\">Drama</option>\n" +
            "<option value=\"3\">Manga</option>\n" +
            "<option value=\"2\">Music</option>\n" +
            "\n" +
            "<option value=\"9\">Music-Video</option>\n" +
            "<option value=\"7\">Raws</option>\n" +
            "<option value=\"4\">Hentai</option>\n" +
            "<option value=\"11\">Batch</option>\n" +
            "<option value=\"5\">Other</option>\n" +
            "</select>\n" +
            "<br />\n" +
            "Torrent Size (Min MB):\n" +
            "<input size=\"4\" type=\"text\" name=\"size_min\" value=\"\" />\n" +
            "(Max MB):\n" +
            "<input size=\"4\" type=\"text\" name=\"size_max\" value=\"\" /><br />\n" +
            "Submitter:\n" +
            "\n" +
            "<input size=\"12\" type=\"text\" name=\"username\" value=\"\" />\n" +
            "<input\ttype=\"submit\" value=\"Search\" /><br />\n" +
            "\t</td></tr>\n" +
            "</table>\n" +
            "</form>\n" +
            "</div><p class=\"footer\">\n" +
            "Plug: Unmetered VPN for just $3.25/month <a href=\"https://www.privateinternetaccess.com/pages/protect-tt/\" title=\"Private Internet Access VPN\">Private Internet Access VPN</a>\n" +
            "<br />\n" +
            "<br />\n" +
            "Rolling visitor numbers <br />IPv4: 24h [122,369] 60min [7,599] 5min [793] 1min [229] - 5 minute rolling average [1562]<br />\n" +
            "IPv6: 24h [1,667] 60min [68] 5min [3] 1min [1] - 5 minute rolling average [9]\n" +
            "\n" +
            "<br />Hosted at: \n" +
            "\n" +
            "<a href=\"http://serioustubes.org/\"><img alt=\"Serious Tubes\" src=\"http://s3.tokyotosho.info/serioustubes.png\" /></a><br />Page generated in 149ms.<br />Haven’t rebooted in: 24 days, 19 hours, 42 minutes, 7 seconds<br />Last <a href=\"donate.php\">donation</a>: 1 month, 4 days, 12 hours, 30 minutes, 56 seconds ago<br />\n" +
            "You are connecting from: 188.244.38.2</p></body></html>";

    List<SearchResult> results = parser.getResults(readWebPage);
    List<SearchResult> expectedResults = new ArrayList<SearchResult>();
    addToExpected(expectedResults, "[Tsuki-Hatsuyuki]_Guilty_Crown_-_06_[704x400][8EE80484].avi",
        "http://www.nyaa.eu/?page=download&amp;tid=261245");
    addToExpected(expectedResults, "[Tsuki-Hatsuyuki]_Guilty_Crown_-_05v2_[704x400][9BAB2393].avi",
        "http://www.nyaa.eu/?page=download&amp;tid=261244");
    assertEquals(expectedResults, results);
  }

  private void addToExpected(List<SearchResult> expectedResults, String title, String href) {
    SearchResult oneExpectedResult = new SearchResult(title, href);
    expectedResults.add(oneExpectedResult);
  }

}
