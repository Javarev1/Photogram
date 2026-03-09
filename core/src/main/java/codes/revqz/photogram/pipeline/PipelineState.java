package codes.revqz.photogram.pipeline;

public enum PipelineState {
    IDLE,
    SPLITTING,
    UPLOADING,
    ASSEMBLING,
    READY,
    FAILED,
    CANCELLED
}
