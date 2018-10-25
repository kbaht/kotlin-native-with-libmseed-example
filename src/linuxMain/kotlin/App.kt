import kotlinx.cinterop.*
import libmseed.MSRecord
import libmseed.ms_readmsr
import libmseed.msr_print

fun main(args: Array<String>) {
    val filename = if (args.isNotEmpty()) args[0] else return
    memScoped {
        val ppmsr = alloc<CPointerVar<MSRecord>>()
        while (ms_readmsr(
                ppmsr.ptr,
                filename,
                -1, null, null, 1, 0, 0
            ) == libmseed.MS_NOERROR
        ) {
            msr_print(ppmsr.value, 0)
        }
    }
}