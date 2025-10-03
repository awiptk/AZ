package eu.kanade.tachiyomi.ui.extension

import android.view.View
import eu.davidea.viewholders.FlexibleViewHolder
import eu.kanade.tachiyomi.databinding.ExtensionDetailItemBinding

/**
 * Holder used to display repo items.
 *
 * @param view The view used by repo items.
 * @param adapter The adapter containing this holder.
 */
class ExtensionDetailsPrefsButtonHolder(view: View, val adapter: ExtensionDetailsPrefsButtonAdapter) : FlexibleViewHolder(view, adapter) {
    private val binding = ExtensionDetailItemBinding.bind(view)

    /**
     * Binds this holder with the given source.
     *
     * @param name The name of the source to bind.
     */
    fun bind(name: String) {
        // Set capitalized title.
        binding.title.text = name
    }
}
