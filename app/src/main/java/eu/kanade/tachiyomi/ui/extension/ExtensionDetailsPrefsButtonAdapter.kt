package eu.kanade.tachiyomi.ui.extension

import eu.davidea.flexibleadapter.FlexibleAdapter

/**
 * Custom adapter for repos.
 *
 * @param controller The containing controller.
 */
class ExtensionDetailsPrefsButtonAdapter(controller: ExtensionDetailsController) :
    FlexibleAdapter<ExtensionDetailsPrefsButtonItem>(null, controller, true)
