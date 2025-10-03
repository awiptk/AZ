package eu.kanade.tachiyomi.ui.extension

import eu.kanade.tachiyomi.source.SourceManager
import eu.kanade.tachiyomi.ui.base.presenter.BasePresenter
import uy.kohesive.injekt.Injekt
import uy.kohesive.injekt.api.get

class ExtensionPreferencesPresenter(
    val sourceId: Long
) : BasePresenter<ExtensionPreferencesController>() {
    private val sourceManager: SourceManager = Injekt.get()

    val source = sourceManager.get(sourceId)
}
