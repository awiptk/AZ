package eu.kanade.tachiyomi.ui.extension

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible
import eu.kanade.tachiyomi.R

/**
 * Repo item for a recycler view.
 */
class ExtensionDetailsPrefsButtonItem(val sourceName: String) : AbstractFlexibleItem<ExtensionDetailsPrefsButtonHolder>() {
    /**
     * Returns the layout resource for this item.
     */
    override fun getLayoutRes(): Int {
        return R.layout.extension_detail_item
    }

    /**
     * Returns a new view holder for this item.
     *
     * @param view The view of this item.
     * @param adapter The adapter of this item.
     */
    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>
    ): ExtensionDetailsPrefsButtonHolder {
        return ExtensionDetailsPrefsButtonHolder(view, adapter as ExtensionDetailsPrefsButtonAdapter)
    }

    /**
     * Binds the given view holder with this item.
     *
     * @param adapter The adapter of this item.
     * @param holder The holder to bind.
     * @param position The position of this item in the adapter.
     * @param payloads List of partial changes.
     */
    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
        holder: ExtensionDetailsPrefsButtonHolder,
        position: Int,
        payloads: List<Any?>?
    ) {
        holder.bind(sourceName)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        return false
    }

    override fun hashCode(): Int {
        return sourceName.hashCode()
    }
}
