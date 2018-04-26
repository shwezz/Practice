Page Title: Politico Actions Page

#Object Definitions
====================================================================================
header-legCompass								css							li.micro-pro-header-legislative-compass-column
new_hamburger									css							button.hamburger
link_under_hamburger							xpath						//div[@class='col is-menu']//a[contains(text(),'Legislative Compass')]
lnk-first-bill									xpath						(//h3[@class='name mt1']//a)[1]
lnk-first-fresh-bill							xpath						(//button[contains(@class,'bell-search') and not(contains(@class,'active')) ]/..//button[@class='js-add-bill-to-folder' ]/../../../..//h3[@class='name mt1']//a[not(contains(text(),'Amdt')) and not(preceding-sibling::a)])[1]
lnk-frst-fresh-amdt								xpath						(//button[contains(@class,'bell-search') and not(contains(@class,'active')) ]/..//button[@class='js-add-bill-to-folder' ]/../../../..//h3[@class='name mt1']//a[contains(text(),'Amdt') and not(preceding-sibling::a)])[1]
btn-bill-header									xpath						//div[contains(@class,'bill-header-button')]//span[text()='${text}']
btn-track-modal									xpath						//div[contains(@class,'track_email_buttons')]/button[contains(text(),'${text}')]
modal-emailAlertSettings						css							div.lc-modal.is-active div.lc-modal__title-bar
txt-chkbox-Actions								css							ul.lined-list.mt1 li input[value='actions']
txt-chkbox-CurrentStatus						css							ul.lined-list.mt1 li input[value='current_status']
txt-chkbox-BillText								css							ul.lined-list.mt1 li input[value='bill_text']
txt-chkbox-ProBillAnalysis						css							ul.lined-list.mt1 li input[value='pro_bill_analysis']
txt-chkbox-cosponsorChanges						css							ul.lined-list.mt1 li input[value='cosponsor_changes']
txt-chkbox-CRSSummaryAreas						css							ul.lined-list.mt1 li input[value='crs_summary_areas']
txt-chkbox-CRSSummary							css							ul.lined-list.mt1 li input[value='crs_summary']
txt-chkbox-amendments							css							ul.lined-list.mt1 li input[value='amendments']
txt-chkbox-documents							css							ul.lined-list.mt1 li input[value='documents']
txt-chkbox-relatedBills							css							ul.lined-list.mt1 li input[value='related_bills']
radio-changeFilter								css							.mt1.lh6.fz3  li input[value='${text}']
close-modal										css							button.close-button
radio-howOften									css							ul.frequency-select input[value='${text}']
btn-emailAlertModal-save						css							button.save_button 
txt-successModal								css							div.lc-modal p b:nth-child(1)
txt-success-addToFolder							xpath						//div[@class='lc-modal__title-bar']/../div[@class='lc-modal__body']//b[@class='c-green-haze']
btn-preferences									xpath						//a[@class='button--shuttlegray']//span[text()='Your Preferences']
txt-trackedBill									xpath						(//td[@class='db']//a)[1]
header-emailAlertSettings						css							div.lc-modal__title-bar
input-enterFolder								css							.awesomplete input[name="folder"]
list-autocompleteHiddenFolderName				css							.awesomplete ul li:nth-child(1)
txt-billNameOnFolder							css							.lc-modal__body .js-bill-folders.dn b
btn-addToFolder-save							css							.lc-modal__body button[type='submit']
btn-addToFolder-cancel							css							.lc-modal .js-close-modal
lnk-jumpTo										xpath						//div[contains(@class,'jump-to')]//li//a[text()='${val}']
items-createdFolder								xpath						(//span[@class='label--folder'])[1]
btn-stopTracking								css							.lc-modal button.stop-tracking
btn-ok-stopTracking								css							.lc-modal .js-close-modal
btn-ok-folderCreated							css							.lc-modal .js-close-modal
btn-remove-trackedLegislation					xpath						(//button[contains(@class,'js-delete-tracked-legislation')])[1]
list-remove-billFromFolder						xpath						(//button[contains(@class,'js-preferences-delete-folder')])[1]
btn-yes-removeTracking							css							button.tracked_legislation_delete
modal-deleteFolder								css							div.lc-modal
btn-yes-removeMain								css							div.lc-modal button.button--link
btn-ok-folderDeleted							css							.lc-modal .js-close-modal
txtarea-createANote								css							div.create-a-note textarea
select-access									css							div.df--m select#access
item-selectAccessShared							css							div.df--m select#access option[value='shared']
input-shareNoteWith								css							div.df--m input.js-note-colleague-input.w1_1
chkbox-sharedNoteSuggestion						css							input[data-name='${text}']
btn-createNote									css							button[value="Share Note"]
btn-createPrivateNote							css							button[value='Create Note']
section-CreateNote								css							.create-a-note
txt-noteOnBillPage								css							.js-note.hr.shared p.js-note-text
div-notes										css							div.js-note:not(.dn)
item-createdNotes								xpath						//table[@data-component="tableBillsWithNotes"]//tbody//tr[@data-id='1']//td//a[not(@class='button--cobalt')]
lnks-showNotes									xpath						//span[text()='show notes']
icon-removeNote									xpath						(//tr[@data-component="note"]//button[@data-click="remove"])[1]
tab-rightRail									xpath						//nav[@class='bill__nav']//li/a[contains(text(),'${text}')]
list-tabsRightRailWithNum						css							nav.bill__nav li a span
headings-overviewPage							xpath						//div[@class='cf']//b[contains(text(),'${text}')]
heading-CRSoverviewPage							xpath						//section[@class='bill__main']//h4[text()='CRS Issue Area: ']	
div-descriptionbillTextPage						css							#bill_text_description			
select-billVersion								css							select#crstext	
select-billVersionState							css							select#billtext	
options-billVersion								css							select#crstext option
options-billVersionState						css							select#billtext option
btn-downloadBillText							css							button#billtext-download-button
col-ActionsTable								xpath						//table[@class="lc-table"]//th[contains(text(),'${text}')]
select-newsContentType							css							select#newsbill
option-newsContents								css							select#newsbill option
option-newsContentType							xpath						//select[@id='newsbill']//option[contains(text(),'${text}')]
btn-billVersion									css							button[data-click="showComparer"]
select-toBillVersion							css							select#bill-compare-to
select-fromBillVersion							css							select#bill-compare-from
options-fromBillVersion							css							select#bill-compare-from option
options-toBillVersion							css							select#bill-compare-to option
btn-compare										css							button[data-click="compare"]
txt-compareKey									css							div.bill-comparer__controls p
list-BillsOnLegSearch							xpath						//h3[@class='name mt1']//a[1][not(contains(text(),'Amdt.')) and not(contains(text(),'Amendment'))]
txt-billTxtCount								css							li#bill_text a span
txt-newsCount									css							li#bill_news a span
btn-closeComparer								css							button[data-click="hideComparer"]
div-versionController							css							div[data-component="versionController"]
div-billComparer								css							div.bill__comparer
txt-CRSSummaryCount								css							li#bill_crs_summary a span
txt-billTextCount								css							li#bill_text a span
select-billTextCRS								css							select[name="billtext"]
summary-CRSBill									css							div#bill_text_description summary_text
label-federalBill								css							header.bill__header span.label--federal
label-stateBill									css							header.bill__header span.label--state
lnk-currentTab									css							li.current a
txt-billNum										css							h1#header-title
txt-billNameDetailPage							css							header.bill__header h2
txt-proTitle									css							span#pro-title
lnk-sponsor										css							header.bill__header li a
lnk-stateSponsor								css							header.bill__header li span
lnk-seeAllActions								xpath						//a[contains(text(),'See All Actions')]
heading-mainBillPage							xpath						//section[@class='bill__main']//h3[contains(text(),'${text}')]
txt-introducedDate								css							p.pt4 time
txt-committees									xpath						//div[@class='cf']//b[contains(text(),'Committees:')]/../a
btn-expandCRS									css							button#togglebutton
txt-otherTitle									xpath						//b[text()='Other Title(s):']/../..//ul//li
heading-proBill									xpath						//h4[contains(text(),'Pro Bill Analysis:')]
select-proBillDate								css							select#probill
date-proBill									css							p.timestamp time
options-proBillDate								css							select#probill option
tab-billText									css							li#bill_text
tab-news										css							li#bill_news
tab-relatedBill									css							li#bill_related
count-billText									css							li#bill_text span
tab-crsSummary									css							li#bill_crs_summary
count-crsSummary								css							li#bill_crs_summary span
tab-actions										css							li#bill_actions
count-actions									css							li#bill_actions span
tab-rollCallVotes								css							li#bill_roll_call_votes
count-rollCallVotes								css							li#bill_roll_call_votes span
tab-amendments									css							li#amendments
count-amendments								css							li#amendments span
tab-cosponsor									css							li#cosponsor
count-cosponsor									css							li#cosponsor span
tab-documents									css							li#documents
count-documents									css							li#documents span
tab-overview									css							li#overview_tab
mark-searchedItems								css							mark[data-markjs="true"]
input-searchField								css							input#search_field
btn-search										css							button#search_button
btn-loadMore									css							button#search-load-more
desc-crsSummary									css							#bill_text_description summary_text
time-actionsBillPage							css							table[data-component="tableSavedSearch"] tbody td time
txt-documentsBillText							xpath						//h4[text()='Bill Text']/..//table[@class='sortTable lc-table']//tbody//tr//td[2]//a[2]
txt-billNameRelatedBill							css							table.js-lc-similar-bill-section-non-pro tbody tr[class=''] td:nth-child(1)
icon-expandSection								css							div.similar-section button.section	
txt-billNameSimilarBill							css							div[class='div-section'] li.results-list__result p.pt1
lnk-billNameSimilarBill							css							div[class='div-section'] li.results-list__result p.pt1 a	
count-newsArticles								css							div#news_list_details article	
btn-loadMoreNews								css							button#news-load-more		
txt-newsHeading									css							div.summary header h1
lnk-newsHeading									css							div.summary header h1 a
txt-NewsTeaser									css							//div[@class='summary']//p[2]
lnk-NewsTeaser									xpath						//div[@class='summary']//p//a[1]
lnk-legislator									xpath						//div[@class='summary']//p//a[2]
date-rollCallVotes								css							table#roll-call-sort tbody tr td:nth-child(1) time
icon-downloadrollDoc							css							table#roll-call-sort tbody tr td:nth-child(2) i.icon-download
label-downloadRollDoc							css							table#roll-call-sort tbody tr td:nth-child(2) a:nth-child(2)
select-rollCallVote								css							select#roll_call_vote_list
option-rollCallVote								css							select#roll_call_vote_list option
head-rollCallVoteSummary						xpath						//div[@id='roll_call_detail']//h4[contains(text(),'Roll Call') and contains(text(),'Vote Summary')]
head-rollCallSubHead							xpath						//div[@id='roll_call_detail']//h4[contains(text(),'${text}')]
txt-voteSummaryModule							xpath						//b[contains(text(),'${text}')]/..
time-voteSummary								xpath						//b[contains(text(),'Date')]/../time
txt-voteSummaryResult							xpath						//b[contains(text(),'Result')]/../b[last()]
head-tableVoteCount								xpath						//thead[@class='vote-count-thead']//tr//th[contains(text(),'${text}')]
subHead-tableVoteCountYes						xpath						//thead[@class='vote-count-thead']//tr//th[contains(text(),'Yes')]//p[text()='${text}']
subHead-tableVoteCountNo						xpath						//thead[@class='vote-count-thead']//tr//th[contains(text(),'No')]//p[text()='${text}']
txt-tableVoteCount								xpath						//table[@class='lc-table']//tbody//td//b[text()='${text}']
data-voteCountCol								xpath						//table[@class='lc-table']//tbody//tr//td[2]
select-RCVotedState								css							select#roll_call_voted_state
option-RCVotedState								css							select#roll_call_voted_state option[value='${text}']
select-RCVoteCast								css							select#roll_call_vote_cast
option-RCVoteCast								css							select#roll_call_vote_cast option[value='${text}']	
txt-listVoteCountMembers						xpath						//div[@id='roll_call_sponsor_list']//li/text()[last()]	
txt-listRepVoteCountMembers						xpath						//div[@id='roll_call_sponsor_list']//li[not(@class='dn')]/a
lnk-listLegislatorsCosponsors					css							div#cosponsors_details td>a
btn-showAllRollCallSponsor						css							div#roll_call_sponsor_list_show_all button
heading-voteCountMember							css							div#roll_call_sponsor div.heading div.fl b		
txt-relatedByCongress							xpath						//div[@class='pt4']//p		
txt-relatedBySimilar							xpath						//h4[text()='Similar Bill Sections']/parent::div/p
head-relatedBillSections						css							section.bill__main h4
time-amendmentOffered							css							table#download-amendments tbody tr td>time
lnk-amendmentBillNum							css							table#download-amendments tbody tr td:nth-child(2)>a
txt-amendmentBill								xpath						//table[@id='download-amendments']//tbody//tr//td[2]
txt-amendmentCongress							css							table#download-amendments tbody tr td:nth-child(2)>div.c-dustygray
txt-amendmentDescription						css							table#download-amendments tbody tr td:nth-child(2)>div:nth-child(3)
lnk-amendmentDescription						xpath						//table[@id='download-amendments']//tbody//tr//td[2]//b[text()='Latest action:']/..//a
lnk-amendmentSponsor							xpath						//table[@id='download-amendments']//tbody//tr//td[2]//b[text()='Sponsor:']/following-sibling::a
txt-amendmentLatestAction						xpath						//table[@id='download-amendments']//tbody//tr//td[2]//b[text()='Latest action:']/..
heading-bill									css							header.bill__header h1
lnk-committeesCosponsorPage						xpath						//section[@class='bill__main']//div//b[text()='Committees:']/../a
txt-committeesCosponsorPage						xpath						//section[@class='bill__main']//div//b[text()='Committees:']/..//li
lnk-subCommitteesCosponsorPage					xpath						//section[@class='bill__main']//div//b[text()='Committees:']/../ul/li/a
head-tableCosponsor								css							table#table-cosponsor thead th
btn-sortCosponsorName							xpath						(//table[@id='table-cosponsor']//thead//th//button)[1]
btn-sortCosponsorDistrict						xpath						(//table[@id='table-cosponsor']//thead//th//button)[2]
btn-sortCosponsorParty							xpath						(//table[@id='table-cosponsor']//thead//th//button)[3]
btn-sortCosponsorDate							xpath						(//table[@id='table-cosponsor']//thead//th//button)[4]
txt-cosponsorName								css							table#table-cosponsor tbody tr td a
txt-cosponsorDistrict							css							table#table-cosponsor tbody tr td:nth-child(2)
txt-cosponsorParty								css							table#table-cosponsor tbody tr td:nth-child(3) span
time-cosponsorDate								css							table#table-cosponsor tbody tr td:nth-child(4) time
lnk-cosponsor									xpath						//p[@class='mt1 lh4']/b[text()='Cosponsors:']//../a
txt-cosponsorCnt								xpath						//section[@class='bill__main']//b[text()='Cosponsor count:']/../p
heading-CDPage									css							div.bio-intro-details.vcard h2
abbr_heading-CDPage								css							div.bio-intro-details.vcard h2 abbr
articles-billNumber								css							h3.name.mt1 a
txt-boilerplateCosponsor						css							section.bill__main p
head-cosponsorsTable							css							section.bill__main h4
head-documentsTab								xpath						//section[@class='bill__main']//h4[text()='${text}']
desc-documentSection							xpath						//section[@class='bill__main']//h4[text()='${text}']/../p
txt-cosponsorNameFrmTable						css							table#detail-table tr td:nth-child(1)
date-docTable									xpath						//section[@class='bill__main']//h4[text()='${text}']/..//tbody//tr/td[1]/time
icon-downloadDocTable							xpath						//section[@class='bill__main']//h4[text()='${text}']/..//tbody//tr/td[2]//a/i
lnk-downloadDocTable							xpath						//section[@class='bill__main']//h4[text()='${text}']/..//tbody//tr/td[2]//a[2]
txt-formatDocTable								xpath						//section[@class='bill__main']//h4[text()='${text}']/..//tbody//tr/td[3]
txt-sizeDocTable								xpath						//section[@class='bill__main']//h4[text()='${text}']/..//tbody//tr/td[4]
btn-disabledCreateNote							css							div.create-a-note button[type="submit"][disabled]
txt-notePostedBy								xpath						//div[contains(@class,'js-note')]//b[starts-with(text(),'Posted by: ')]
txt-noteDateOfPublish							css							div.js-note:not(.dn) span time
txt-notePublished								xpath						//div[contains(@class,'js-note') and not(contains(@class,'dn'))]//div[@class='media--lc-global-notes']/p
btn-showAllNotes								xpath						//button[text()='Show All']
track_button_billPage							css							div.bill-header__buttons button.track_button
selectAllLink									css							div.lc-modal-email-alert a.select_all
Notetext_billDetailPage							xpath						//div[@class='recent-notes']//p[contains(text(),'${val}')]		
select_news_contentType							css							section.bill__main select#newsbill	
option_newsContentType							xpath						//section[@class='bill__main']//select[@id='newsbill']/option[contains(text(),'${val}')]
contentTypeNews									css							div#news_list_details ul>li div.summary>b
btn_sort_relatedBills							xpath						//table[contains(@class,'related-bill-table')]//th[contains(text(),'${val}')]/button
txt-billBill									css							table.js-lc-similar-bill-section-non-pro tbody tr:not(.dn) td:nth-child(1) a
txt-billSponsor									css							table.js-lc-similar-bill-section-non-pro tbody tr:not(.dn) td:nth-child(2) a
txt-billIdentified								css							table.js-lc-similar-bill-section-non-pro tbody tr:not(.dn) td:nth-child(3)
txt-billJustification							css							table.js-lc-similar-bill-section-non-pro tbody tr:not(.dn) td:nth-child(4) 
noOfRelatedBills								css							table.js-lc-similar-bill-section-non-pro tbody tr:not(.dn)
relatedBill_ShwAll								css							table.js-lc-similar-bill-section-non-pro+div a#showAll-non-pro
noOfProrelatedBills								css							table.js-lc-similar-bill-section-pro tbody tr:not(.dn)
pro_relatedBills_ShowALL						css							table.js-lc-similar-bill-section-pro+div a#showAll
folderShowContent								css							table[data-component='tableFolders']>tbody>tr:nth-child(1) td.show-results>button
foldercontentCount								css							table[data-component='tableFolders']>tbody>tr:nth-child(1) td:nth-child(3)
deleteFolderbILL								css							table[data-component='tableFolderBills']>tbody>tr:nth-child(1) button.icon-close
lc_modalWindow									css							div.lc-modal
================================================================================================================================